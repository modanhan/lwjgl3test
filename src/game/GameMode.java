package game;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import events.EventHandler;
import game.Enemy.EnemyBullet;
import game.Player.PlayerBullet;
import util.Keyboard;

public class GameMode implements Mode {
	static Player player;
	static List<PlayerBullet> playerbullets = new LinkedList<PlayerBullet>();
	static List<EnemyBullet> enemybullets = new LinkedList<EnemyBullet>();

	public GameMode() {
		GLFW.glfwSetWindowTitle(GlobalVars.window, "press esc to quit game");
		Game.init();
		player = new Player();
		Game.add(player);
		Enemy e1 = new Enemies.SniperEnemy(GlobalVars.WIDTH*3/4, GlobalVars.HEIGHT*3/4);
		Enemy e2 = new Enemies.ShooterEnemy(GlobalVars.WIDTH*1/4, GlobalVars.HEIGHT*3/4);
		Game.add(e1);
		Game.add(e2);
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
