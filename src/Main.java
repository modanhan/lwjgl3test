import events.EventHandler;
import global.Global;
import graphics.Graphics;
import mode.ModeHandler;

import org.lwjgl.opengl.*;

import util.Console;
import util.Keyboard;
import util.Time;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {
	private static Keyboard keyboard;
	private static Thread consolethread;
	static void init() {
		Global.running = true;

		glfwInit();
		glfwWindowHint(GLFW_SAMPLES, 4);
		glfwWindowHint(GLFW_FLOATING,1);
		Global.window = glfwCreateWindow(Global.WIDTH,
				Global.HEIGHT, "placeholder", NULL, NULL);
		glfwMakeContextCurrent(Global.window);
		glfwSwapInterval(1);
		glfwShowWindow(Global.window);

		GL.createCapabilities();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Global.WIDTH, 0, Global.HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		Time.init();

		Time.update();
		EventHandler.init();
		ModeHandler.init();
		consolethread = new Thread(new Console());
		consolethread.start();
		keyboard = new Keyboard();
		glfwSetKeyCallback(Global.window, keyboard);
	}

	static void update() {
		Graphics.clearBuffers();
		
		Time.update();
		ModeHandler.update();
		EventHandler.update();

		Keyboard.update();
		glfwSwapBuffers(Global.window);
		glfwPollEvents();
	}

	static void exit() {
		consolethread.interrupt();
		glfwDestroyWindow(Global.window);
	}

	public static void main(String[] args) {
		init();
		while (Global.running) {
			update();
			if (glfwWindowShouldClose(Global.window) == GL_TRUE) {
				Global.running = false;
			}
		}
		exit();
	}

}
