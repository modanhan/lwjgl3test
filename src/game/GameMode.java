package game;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import events.EventHandler;
import game.Player.PlayerBullet;
import util.Keyboard;

public class GameMode implements Mode {
	static Player player;
	static List<PlayerBullet> playerbullets = new LinkedList<PlayerBullet>();

	public GameMode() {
		GLFW.glfwSetWindowTitle(GlobalVars.window, "press esc to quit game");
		Game.init();
		player = new Player();
		Game.add(player);
		Enemy e = new Enemies.BasicEnemy(GlobalVars.WIDTH*3/4, GlobalVars.HEIGHT/2);
		Game.add(e);
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			ModeHandler.setMode(new StartMode());
			EventHandler.clear();
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_K)) {
			System.out.println("player killed");
			EventHandler.clear();
			Game.remove(player);
		}
		Game.update();
	}

}
