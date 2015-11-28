import events.EventHandler;
import game.GlobalVars;
import game.ModeHandler;
import graphics.Graphics;

import org.lwjgl.opengl.*;

import util.Console;
import util.Keyboard;
import util.Time;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {
	static Thread consolethread;
	static void init() {
		GlobalVars.running = true;

		glfwInit();
		glfwWindowHint(GLFW_SAMPLES, 4);
		GlobalVars.window = glfwCreateWindow(GlobalVars.WIDTH,
				GlobalVars.HEIGHT, "placeholder", NULL, NULL);

		glfwMakeContextCurrent(GlobalVars.window);
		glfwSwapInterval(1);
		glfwShowWindow(GlobalVars.window);

		GL.createCapabilities();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, GlobalVars.WIDTH, 0, GlobalVars.HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		Time.init();

		Time.update();
		EventHandler.init();
		ModeHandler.init();
		consolethread = new Thread(new Console());
		consolethread.start();
		glfwSetKeyCallback(GlobalVars.window, new Keyboard());
	}

	static void update() {
		Graphics.clearBuffers();
		
		Time.update();
		ModeHandler.update();
		EventHandler.update();

		Keyboard.update();
		glfwSwapBuffers(GlobalVars.window);
		glfwPollEvents();
	}

	static void exit() {
		consolethread.interrupt();
		glfwDestroyWindow(GlobalVars.window);
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
