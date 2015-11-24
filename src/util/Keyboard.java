package util;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {

	static boolean[] down = new boolean[349], lastdown = new boolean[349];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		down[key] = action != GLFW.GLFW_RELEASE;
	}

	public static void update() {
		for (int i = 0; i < 349; i++)
			lastdown[i] = down[i];
	}

	public static boolean isKeyDown(int keycode) {
		return down[keycode];
	}

	public static boolean isKeyPressed(int keycode) {
		return down[keycode] && (!lastdown[keycode]);
	}

	public static boolean isKeyReleased(int keycode) {
		return (!down[keycode]) && lastdown[keycode];
	}
}
