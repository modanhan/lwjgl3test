package game.mode;

import org.lwjgl.glfw.GLFW;

import global.Global;
import util.Keyboard;

public class StartMode implements Mode {

	public StartMode() {
		GLFW.glfwSetWindowTitle(Global.window,
				"press enter to play, esc to exit");
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ENTER)) {
			ModeHandler.setMode(new GameMode());
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			Global.running = false;
		}
	}

}
