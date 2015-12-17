package game;

import game.object.CircleGameObject;
import game.object.Enemy;
import game.object.GameObject;

public class Collision {
	public static void update() {
		player_enemybullet();
		enemy_playerbullet();
	}

	private static void player_enemybullet() {
		if (Game.player == null) {
			return;
		}
		for (GameObject g : Game.enemybullets) {
			if (CircleGameObject.checkCollision(Game.player,
					(CircleGameObject) g)) {
				Game.player.kill();
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
					g.remove();
				}
			}
		}
	}
}
