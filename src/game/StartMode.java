package game;

import org.lwjgl.glfw.GLFW;

import util.Keyboard;

public class StartMode implements Mode {

	public StartMode() {
		GLFW.glfwSetWindowTitle(GlobalVars.window,
				"press enter to play, esc to exit");
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ENTER)) {
			ModeHandler.setMode(new GameMode());
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			GlobalVars.running = false;
		}
	}

}
