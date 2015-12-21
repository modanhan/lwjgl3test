package mode;

import org.lwjgl.glfw.GLFW;

import events.EventHandler;
import game.Game;
import game.ui.UI;
import util.Global;
import util.Keyboard;

public class GameMode implements Mode {

	public GameMode() {
		GLFW.glfwSetWindowTitle(Global.window, "press esc to quit game");
		Game.init();
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			UI.clear();
			ModeHandler.setMode(new StartMode());
			EventHandler.clear();
		}
		Game.update();
	}

}
