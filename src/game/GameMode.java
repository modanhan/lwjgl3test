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
	static List<Enemy> enemies = new LinkedList<Enemy>();

	public GameMode() {
		GLFW.glfwSetWindowTitle(GlobalVars.window, "press esc to quit game");
		Game.init();
		player = new Player();
		playerbullets.clear();
		enemybullets.clear();
		enemies.clear();
		Game.add(player);
		Enemy e1 = new Enemies.BasicEnemy(GlobalVars.WIDTH*2/4, GlobalVars.HEIGHT*3/4);
		Enemy e2 = new Enemies.ShooterEnemy(GlobalVars.WIDTH*1/4, GlobalVars.HEIGHT*3/4);
		Enemy e3 = new Enemies.SniperEnemy(GlobalVars.WIDTH*1/4, GlobalVars.HEIGHT+100);
		Enemy e4 = new Enemies.SniperEnemy(GlobalVars.WIDTH*3/4, GlobalVars.HEIGHT+100);
		Enemy e5 = new Enemies.SniperEnemy(GlobalVars.WIDTH*2/4, -100);
		Enemy e6 = new Enemies.BossEnemy(GlobalVars.WIDTH*2/4, GlobalVars.HEIGHT+100);
		e1.spawn();
		e2.spawn();
		EventHandler.add(new Enemy.EnemyDespawnEvent(4000,e2));
		EventHandler.add(new Enemy.EnemyMoveEvent(5000,e1,GlobalVars.WIDTH*2/4, GlobalVars.HEIGHT+100));
		EventHandler.add(new Enemy.EnemySpawnEvent(5000,e3));
		EventHandler.add(new Enemy.EnemySpawnEvent(5000,e4));

		EventHandler.add(new Enemy.EnemyMoveEvent(5000,e3,GlobalVars.WIDTH*1/4, GlobalVars.HEIGHT*3/4));
		EventHandler.add(new Enemy.EnemyMoveEvent(5000,e4,GlobalVars.WIDTH*3/4, GlobalVars.HEIGHT*3/4));

		EventHandler.add(new Enemy.EnemyDespawnEvent(7000,e1));

		EventHandler.add(new Enemy.EnemySpawnEvent(10000,e5));
		EventHandler.add(new Enemy.EnemyMoveEvent(10000,e5,GlobalVars.WIDTH*2/4, GlobalVars.HEIGHT*1/4));

		EventHandler.add(new Enemy.EnemyDespawnEvent(15000,e3));
		EventHandler.add(new Enemy.EnemyDespawnEvent(15000,e4));
		EventHandler.add(new Enemy.EnemyDespawnEvent(15000,e5));

		EventHandler.add(new Enemy.EnemySpawnEvent(15000,e6));
		EventHandler.add(new Enemy.EnemyMoveEvent(15000,e6,GlobalVars.WIDTH*2/4, GlobalVars.HEIGHT*3/4));

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
