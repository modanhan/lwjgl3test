package util;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {
	private static Keyboard k;
	static boolean[] down = new boolean[349], lastdown = new boolean[349];
	private Keyboard(){
		
	}
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		down[key] = action != GLFW.GLFW_RELEASE;
	}
	public static void init(){
		k = new Keyboard();
		glfwSetKeyCallback(Global.window, k);
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
	
	public static int size(){
		return 349;
	}
}
