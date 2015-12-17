import events.EventHandler;
import game.ui.UI;
import graphics.Graphics;
import mode.ModeHandler;

import org.lwjgl.opengl.*;

import util.Console;
import util.Global;
import util.Keyboard;
import util.Mouse;
import util.Time;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Toolkit;

import org.lwjgl.glfw.GLFW;

public class Main {
	private static Thread consolethread;

	static void init() {
		Global.running = true;

		glfwInit();
		glfwWindowHint(GLFW_SAMPLES, 4);
		glfwWindowHint(GLFW_FLOATING, 1);
		Global.width = Global.fullscreen ? Toolkit.getDefaultToolkit().getScreenSize().width : Global.width;
		Global.height = Global.fullscreen ? Toolkit.getDefaultToolkit().getScreenSize().height : Global.height;
		Global.length = Global.height;
		Global.window = glfwCreateWindow(Global.width, Global.height, "placeholder",
				Global.fullscreen ? GLFW.glfwGetPrimaryMonitor() : NULL, NULL);
		glfwMakeContextCurrent(Global.window);
		glfwSwapInterval(1);
		glfwShowWindow(Global.window);

		GL.createCapabilities();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Global.width, 0, Global.height, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		Time.init();

		Time.update();
		EventHandler.init();
		ModeHandler.init();
		consolethread = new Thread(new Console());
		consolethread.start();
		Mouse.init();
		Keyboard.init();
	}

	static void update() {
		Graphics.clearBuffers();

		Time.update();
		ModeHandler.update();
		UI.update();
		EventHandler.update();
		Mouse.update();
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
