package game;

import org.lwjgl.glfw.GLFW;

import util.Keyboard;

public class GameMode implements Mode{
	
	public GameMode() {
		GLFW.glfwSetWindowTitle(GlobalVars.window, "in game");
	}

	@Override
	public void update() {
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
			System.out.println("exiting game");
			ModeHandler.setMode(new StartMode());
		}
		
	}

}
