package mode;

import org.lwjgl.glfw.GLFW;

import game.ui.Button;
import game.ui.UI;
import util.Global;
import util.Keyboard;

public class StartMode implements Mode {

	public StartMode() {
		GLFW.glfwSetWindowTitle(Global.window,
				"press enter to play, esc to exit");
		UI.addElement(new Button(Global.width/2, Global.height/2,100,50){
			@Override
			public void onClick() {
				ModeHandler.setMode(new GameMode());
				UI.clear();
			}
		});
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ENTER)) {
			ModeHandler.setMode(new GameMode());
			UI.clear();
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			Global.running = false;
		}
	}

}
