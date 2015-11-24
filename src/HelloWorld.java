import game.GlobalVars;
import game.ModeHandler;

import org.lwjgl.opengl.*;

import graphics.FrameBuffer;
import graphics.Shader;
import graphics.Texture;
import util.Keyboard;
import util.Time;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;

import static org.lwjgl.system.MemoryUtil.*;

import java.io.File;

public class HelloWorld {
	static final int WIDTH = 720, HEIGHT = 480;

	static Keyboard keyboard;
	
	static FrameBuffer framebufferh;
	static FrameBuffer framebufferv;
	static Texture t;
	static Shader blurh;
	static Shader blurv;
	static void init() {
		GlobalVars.running = true;

		glfwInit();

		GlobalVars.window = glfwCreateWindow(WIDTH, HEIGHT, "placeholder", NULL, NULL);

		glfwMakeContextCurrent(GlobalVars.window);
		glfwSwapInterval(1);
		glfwShowWindow(GlobalVars.window);

		GL.createCapabilities();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		blurh = new Shader();
		blurh.attachVertexShader(Shader.readFromFile("shaders/mainvertex.glsl"));
		blurh.attachFragmentShader(Shader.readFromFile("shaders/blurhfragment.glsl"));
		blurh.link();
		glUseProgram(blurh.getID());
		int loc = glGetUniformLocation(blurh.getID(), "texture");
	    GL20.glUniform1i(loc, 0); 
	    blurv = new Shader();
		blurv.attachVertexShader(Shader.readFromFile("shaders/mainvertex.glsl"));
		blurv.attachFragmentShader(Shader.readFromFile("shaders/blurvfragment.glsl"));
		blurv.link();
		glUseProgram(blurv.getID());
		loc = glGetUniformLocation(blurv.getID(), "texture");
	    GL20.glUniform1i(loc, 0); 
		glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		t = Texture.loadTexture(new File("res/glow.png"));
		framebufferh = new FrameBuffer(WIDTH,HEIGHT);
		framebufferv = new FrameBuffer(WIDTH,HEIGHT);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		Time.init();
		ModeHandler.init();
		keyboard = new Keyboard();
		glfwSetKeyCallback(GlobalVars.window, keyboard);
	}

	static int px = WIDTH/2, py = HEIGHT/2;
	static final float SPEED = .25f;

	static void update() {
		Time.update();
		ModeHandler.update();

		if(Keyboard.isKeyPressed(GLFW_KEY_SPACE)){
			System.out.println("space pressed");
		}
		if(Keyboard.isKeyReleased(GLFW_KEY_SPACE)){
			System.out.println("space released");
		}

		int d = (int) (SPEED * Time.getDelta());
		if (Keyboard.isKeyDown(GLFW_KEY_UP)) {
			py += d;
		}
		if (Keyboard.isKeyDown(GLFW_KEY_DOWN)) {
			py -= d;
		}
		if (Keyboard.isKeyDown(GLFW_KEY_LEFT)) {
			px -= d;
		}
		if (Keyboard.isKeyDown(GLFW_KEY_RIGHT)) {
			px += d;
		}
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferh.getID());
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(1, 1, 1);
		glUseProgram(0);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_TRIANGLE_FAN);
		for (float x = 0; x < 360; x += 1) {
			glTexCoord2d(Math.cos(x / 180 * Math.PI)/2f+0.5f, Math.sin(x / 180 * Math.PI)/2f+0.5f);
			glVertex2f((float) Math.cos(x / 180 * Math.PI) * 100 + px, (float) Math.sin(x / 180 * Math.PI) * 100 + py);
		}
		glEnd();

		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferv.getID());
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glUseProgram(blurh.getID());
		glBindTexture(GL_TEXTURE_2D, framebufferh.getTexture().getID());
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(0, 1);	glVertex2f(0, HEIGHT);
		glTexCoord2f(1, 1);	glVertex2f(WIDTH, HEIGHT);
		glTexCoord2f(1, 0);	glVertex2f(WIDTH, 0);
		glEnd();
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(1, 1, 1);
		glUseProgram(0);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_TRIANGLE_FAN);
		for (float x = 0; x < 360; x += 1) {
			glTexCoord2d(Math.cos(x / 180 * Math.PI)/2f+0.5f, Math.sin(x / 180 * Math.PI)/2f+0.5f);
			glVertex2f((float) Math.cos(x / 180 * Math.PI) * 100 + px, (float) Math.sin(x / 180 * Math.PI) * 100 + py);
		}
		glEnd();
		glUseProgram(blurv.getID());
		glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		glBindTexture(GL_TEXTURE_2D, framebufferv.getTexture().getID());
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(0, 1);	glVertex2f(0, HEIGHT);
		glTexCoord2f(1, 1);	glVertex2f(WIDTH, HEIGHT);
		glTexCoord2f(1, 0);	glVertex2f(WIDTH, 0);
		glEnd();
		
		Keyboard.update();
		
		glfwSwapBuffers(GlobalVars.window);
		glfwPollEvents();
	}

	static void exit() {

	}

	public static void main(String[] args) {
		init();
		while (GlobalVars.running) {
			update();
			if (glfwWindowShouldClose(GlobalVars.window) == GL_TRUE) {
				GlobalVars.running = false;
			}
		}
		exit();
	}

}
