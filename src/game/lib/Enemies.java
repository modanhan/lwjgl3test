package game.lib;

import events.EventHandler;
import game.Game;
import game.object.Enemy;
import util.Global;

public class Enemies {
	public static class BasicEnemy extends Enemy {
		{
			hp = 20;
			size = 12;
		}

		public BasicEnemy(float px, float py) {
			super(px, py);
		}

		@Override
		public void update() {
			super.update();
		}

		public void render() {
			super.render();
		}
	}

	public static class ShooterEnemy extends Enemy {
		{
			hp = 20;
			size = 12;
		}

		public ShooterEnemy(float px, float py) {
			super(px, py);
		}

		public void spawn() {
			super.spawn();
			EventHandler.add(new EnemyShooterBulletEvent(1000));
		}

		@Override
		public void update() {
			super.update();
		}

		public void render() {
			super.render();
		}

		class EnemyShooterBulletEvent extends EnemyBulletEvent {

			public EnemyShooterBulletEvent(long time) {
				super(time);
			}

			@Override
			public void run() {
				if (hp <= 0)
					return;
				Game.addEnemyBullet(new EnemyBullet(px, py, Global.Dir.DOWN, Global.shooter_enemy_bullet_speed));
				EventHandler.add(new EnemyShooterBulletEvent(Global.shooter_enemy_bullet_delay));
			}
		}
	}

}
