import game.GlobalVars;
import game.ModeHandler;

import org.lwjgl.opengl.*;

import util.Keyboard;
import util.Time;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HelloWorld {
	static final int WIDTH = 720, HEIGHT = 480;

	static Keyboard keyboard;

	static void init() {
		GlobalVars.running = true;

		glfwInit();

		GlobalVars.window = glfwCreateWindow(WIDTH, HEIGHT, "palce holder", NULL, NULL);

		glfwMakeContextCurrent(GlobalVars.window);
		glfwSwapInterval(1);
		glfwShowWindow(GlobalVars.window);

		GL.createCapabilities();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		Time.init();
		ModeHandler.init();
		keyboard = new Keyboard();
		glfwSetKeyCallback(GlobalVars.window, keyboard);
	}

	static int px = 0, py = 0;
	static final float SPEED = .25f;

	static void update() {
		Time.update();
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
			glVertex2f((float) Math.cos(x / 180 * Math.PI) * 100 + px, (float) Math.sin(x / 180 * Math.PI) * 100 + py);
		}
		glEnd();
		
		ModeHandler.update();

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