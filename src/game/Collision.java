package game;

import game.object.CircleGameObject;
import game.object.Enemy;
import game.object.GameObject;
import game.object.Laser;

public class Collision {
	public static void update() {
		player_enemybullet();
		player_enemylaser();
		player_enemy();
		enemy_playerbullet();
		enemy_playerlaser();
	}

	private static void player_enemybullet() {
		if (Game.player == null)
			return;
		for (GameObject g : Game.enemybullets) {
			if (CircleGameObject.checkCollision(Game.player,
					(CircleGameObject) g)) {
				Game.player.kill();
				g.kill();
				break;
			}
		}
	}
	private static void player_enemylaser() {
		if (Game.player == null)
			return;
		for (GameObject g : Game.enemylasers) {
			if (CircleGameObject.checkCollision(Game.player,
					(CircleGameObject) g)) {
				Game.player.kill();//TODO
				g.kill();
				break;
			}
		}
	}
	private static void enemy_playerbullet() {
		for (GameObject g : Game.playerbullets) {
			for (GameObject h : Game.enemies) {
				if (CircleGameObject.checkCollision((CircleGameObject) g,
						(CircleGameObject) h)) {
					((Enemy) h).takeHit();
					g.kill();
				}
			}
		}
	}
	private static void enemy_playerlaser() {
		for (GameObject g : Game.playerlasers) {
			for (GameObject h : Game.enemies) {
				if (Laser.checkCollision((Laser) g, (CircleGameObject) h)) {
					((Enemy) h).takeHit(((Laser) g).getDamage());
				}
			}
		}
	}
	private static void player_enemy() {
		if (Game.player == null)
			return;
		for (GameObject g : Game.enemies) {
			if (CircleGameObject.checkCollision(Game.player,
					(CircleGameObject) g)) {
				Game.player.kill();
			}
		}
	}
}
