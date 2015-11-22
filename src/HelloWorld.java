import org.lwjgl.opengl.*;

import events.Event;
import events.EventHandler;
import graphics.Shader;
import graphics.Texture;
import util.Keyboard;
import util.Time;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.File;

public class HelloWorld {
	private static long window;
	private static boolean running;
	static final int WIDTH = 720, HEIGHT = 480;

	static Keyboard keyboard;

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
		Shader s = new Shader();
		s.attachVertexShader(Shader.readFromFile("shaders/mainvertex.glsl"));
		s.attachFragmentShader(Shader.readFromFile("shaders/mainfragment.glsl"));
		s.link();
		glUseProgram(s.getID());
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		Texture t = Texture.loadTexture(new File("res/glow.png"));
		glBindTexture(GL_TEXTURE_2D, t.getID());
		Time.init();
		Time.update();
		EventHandler.init();
		
		EventHandler.add(new Event(1000) {
			@Override
			public void run() {
				System.out.println(+(Time.getTimeMillis())
						+ "\t: event 0 triggered");
			}
		});
		EventHandler.add(new Event(5000) {
			@Override
			public void run() {
				System.out.println(+(Time.getTimeMillis())
						+ "\t: event 1 triggered");
			}
		});
		EventHandler.add(new Event(5000) {
			@Override
			public void run() {
				System.out.println(+(Time.getTimeMillis())
						+ "\t: event 2 triggered");
			}
		});
		
		keyboard = new Keyboard();
		glfwSetKeyCallback(window, keyboard);
	}

	static int px = 0, py = 0;
	static final float SPEED = .25f;

	static void update() {
		Time.update();
		EventHandler.update();
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glColor3f(1, 1, 1);

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

		glBegin(GL_TRIANGLE_FAN);
		for (float x = 0; x < 360; x += 1) {
			glTexCoord2d(Math.cos(x / 180 * Math.PI) / 2f + 0.5f,
					Math.sin(x / 180 * Math.PI) / 2f + 0.5f);
			glVertex2f((float) Math.cos(x / 180 * Math.PI) * 100 + px,
					(float) Math.sin(x / 180 * Math.PI) * 100 + py);
		}
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