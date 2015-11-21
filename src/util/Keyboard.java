package util;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {

	static boolean[] down = new boolean[65536], lastdown = new boolean[65536];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		down[key] = action != GLFW.GLFW_RELEASE;
	}

	public static boolean isKeyDown(int keycode) {
		return down[keycode];
	}
}
