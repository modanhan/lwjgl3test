package util;

import org.lwjgl.glfw.GLFW;

public class Konami {
	private static int[] seq = { GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_UP,
			GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_LEFT,
			GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_LEFT, GLFW.GLFW_KEY_RIGHT,
			GLFW.GLFW_KEY_B, GLFW.GLFW_KEY_A, };
	private static int index;

	public static void init() {
		index = 0;
	}

	public static void update() {
		for (int i = 0; i < Keyboard.size(); i++) {
			if (Keyboard.isKeyPressed(i) && i != seq[index]) {
				index = 0;
				return;
			}
		}
		if (Keyboard.isKeyPressed(seq[index])) {
			index++;
		}
		if (index >= seq.length) {
			System.out.println("KONAMI ACTIVATED");
			konami();
			index = 0;
		}
	}

	private static void konami() {

	}
}
