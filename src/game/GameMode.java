package game;

import org.lwjgl.glfw.GLFW;

import util.Keyboard;

public class GameMode implements Mode {
	Player p;

	public GameMode() {
		GLFW.glfwSetWindowTitle(GlobalVars.window, "press esc to quit game");
		Game.init();
		p = new Player();
		Game.add(p);
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			ModeHandler.setMode(new StartMode());
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_K)) {
			System.err.println("player killed");
			Game.remove(p);
		}
		Game.update();
	}

}
