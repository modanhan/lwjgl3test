package game;

import org.lwjgl.glfw.GLFW;

import events.EventHandler;
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
			EventHandler.clear();
		}
		// TODO player is sometimes unkillable
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_K)) {
			System.out.println("player killed");
			EventHandler.clear();
			Game.remove(p);
		}
		Game.update();
	}

}
