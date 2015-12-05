package game;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import events.EventHandler;
import game.Enemy.EnemyBullet;
import game.Player.PlayerBullet;
import util.Keyboard;

public class GameMode implements Mode {
	public static Player player;
	public static Player deadplayer;
	public static List<PlayerBullet> playerbullets = new LinkedList<PlayerBullet>();
	public static List<EnemyBullet> enemybullets = new LinkedList<EnemyBullet>();
	public static List<Enemy> enemies = new LinkedList<Enemy>();
	public static Level level;
	public GameMode() {
		GLFW.glfwSetWindowTitle(GlobalVars.window, "press esc to quit game");
		Game.init();
		player = new Player();
		deadplayer = player;
		Game.add(player);
		reset();
		Level.level(1);

	}
	public static void resetPlayer(){
		player = deadplayer;
		Game.add(player);
		player.shoot();
	}
	public static void reset(){
		Game.removeAll(playerbullets);
		playerbullets.clear();
		Game.removeAll(enemybullets);
		enemybullets.clear();
		Game.removeAll(enemies);
		enemies.clear();
		
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
		if(level.isDone()){
			level.done();
		}
	}

}
