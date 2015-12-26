package game;

import game.object.CollidingGameObject;
import game.object.enemy.Enemy;

public class Collision {
	public static void update() {
		player_enemybullet();
		player_enemy();
		enemy_playerbullet();
	}

	private static void player_enemybullet() {
		if (Game.player == null)
			return;
		for (CollidingGameObject g : Game.enemybullets) {
			if (collide(Game.player, g)) {
				Game.player.kill();
				g.kill();
				break;
			}
		}
	}

	private static void enemy_playerbullet() {
		for (CollidingGameObject g : Game.playerbullets) {
			for (Enemy h : Game.enemies) {
				if (collide(g, h)) {
					((Enemy) h).takeHit();
					g.kill();
				}
			}
		}
	}

	private static void player_enemy() {
		if (Game.player == null)
			return;
		for (CollidingGameObject g : Game.enemies) {
			if (collide(Game.player, g)) {
				Game.player.kill();
			}
		}
	}

	/**
	 * Checks if 2 shapes a, b collide.
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean collide(CollidingGameObject a, CollidingGameObject b) {
		if (a.getShape() == CollidingGameObject.LINE) {
			if (b.getShape() == CollidingGameObject.LINE) {
				return checkLineLineIntersection(a, b);
			} else if (b.getShape() == CollidingGameObject.CIRCLE) {
				return checkLineCircleIntersection(a, b);
			}
		} else if (a.getShape() == CollidingGameObject.CIRCLE) {
			if (b.getShape() == CollidingGameObject.LINE) {
				return checkLineCircleIntersection(b, a);
			} else if (b.getShape() == CollidingGameObject.CIRCLE) {
				return checkCircleCircleIntersection(a, b);
			}
		}
		return false;
	}

	//TODO fix these
	public static boolean checkLineLineIntersection(CollidingGameObject a,
			CollidingGameObject b) {
		return false;
	}

	public static boolean checkCircleCircleIntersection(CollidingGameObject a,
			CollidingGameObject b) {
		return (Math.hypot(b.x - a.x, b.y - a.y) <= a.radius + b.radius);
	}

	public static boolean checkLineCircleIntersection(CollidingGameObject a,
			CollidingGameObject b) {
		float bdir = (float) Math.atan2(b.y - a.y, b.x - a.x);
		float dist = (float) Math.hypot(b.y - a.y, b.x - a.x);
		if (Math.abs(Math.sin(a.dir - bdir)) * dist < b.radius) {
			if (Math.cos(a.dir - bdir) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
