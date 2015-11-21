package game;

import org.lwjgl.glfw.GLFW;

import util.Keyboard;

public class StartMode implements Mode{
	
	public StartMode(){
		GLFW.glfwSetWindowTitle(GlobalVars.window, "start screen");
	}

	@Override
	public void update() {
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_ENTER)){
			// switch to game mode
			System.out.println("entering game");
			ModeHandler.setMode(new GameMode());
		}
		
	}

}
