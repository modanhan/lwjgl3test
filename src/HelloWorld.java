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
	private static long window;
	private static boolean running;
	static final int WIDTH = 720, HEIGHT = 480;

	static Keyboard keyboard;
	
	static FrameBuffer framebuffer;
	static Texture t;
	static void init() {
		running = true;

		glfwInit();

		window = glfwCreateWindow(WIDTH, HEIGHT, "placeholder", NULL, NULL);

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);

		GL.createCapabilities();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	/*	Shader s = new Shader();
		s.attachVertexShader(Shader.readFromFile("shaders/mainvertex.glsl"));
		s.attachFragmentShader(Shader.readFromFile("shaders/mainfragment.glsl"));
		s.link();
		glUseProgram(s.getID());
		int loc = glGetUniformLocation(s.getID(), "texture");
	    GL20.glUniform1i(loc, 0); */
		glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		t = Texture.loadTexture(new File("res/glow.png"));
		framebuffer = new FrameBuffer(WIDTH,HEIGHT);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, t.getID());

		Time.init();
		keyboard = new Keyboard();
		glfwSetKeyCallback(window, keyboard);
	}

	static int px = WIDTH/2, py = HEIGHT/2;
	static final float SPEED = .25f;

	static void update() {
		Time.update();



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
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebuffer.getID());
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, t.getID());
		glBegin(GL_TRIANGLE_FAN);
		for (float x = 0; x < 360; x += 1) {
			glTexCoord2d(Math.cos(x / 180 * Math.PI)/2f+0.5f, Math.sin(x / 180 * Math.PI)/2f+0.5f);
			glVertex2f((float) Math.cos(x / 180 * Math.PI) * 100 + px, (float) Math.sin(x / 180 * Math.PI) * 100 + py);
		}
		glEnd();
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBegin(GL_TRIANGLE_FAN);
		for (float x = 0; x < 360; x += 1) {
			glTexCoord2d(Math.cos(x / 180 * Math.PI)/2f+0.5f, Math.sin(x / 180 * Math.PI)/2f+0.5f);
			glVertex2f((float) Math.cos(x / 180 * Math.PI) * 100 + px, (float) Math.sin(x / 180 * Math.PI) * 100 + py);
		}
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(0, 1);	glVertex2f(0, HEIGHT/3+2);
		glTexCoord2f(1, 1);	glVertex2f(WIDTH/3+2, HEIGHT/3+2);
		glTexCoord2f(1, 0);	glVertex2f(WIDTH/3+2, 0);
		glColor3f(0, 0, 0);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(0, 1);	glVertex2f(0, HEIGHT/3);
		glTexCoord2f(1, 1);	glVertex2f(WIDTH/3, HEIGHT/3);
		glTexCoord2f(1, 0);	glVertex2f(WIDTH/3, 0);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, framebuffer.getTexture().getID());
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(0, 1);	glVertex2f(0, HEIGHT/3);
		glTexCoord2f(1, 1);	glVertex2f(WIDTH/3, HEIGHT/3);
		glTexCoord2f(1, 0);	glVertex2f(WIDTH/3, 0);
		glEnd();
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	static void exit() {

	}

	public static void main(String[] args) {
		init();
		while (running) {
			update();
			if (glfwWindowShouldClose(window) == GL_TRUE) {
				running = false;
			}
		}
		exit();
	}

}