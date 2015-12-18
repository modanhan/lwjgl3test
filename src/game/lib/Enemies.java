package game.lib;

import events.Event;
import events.EventHandler;
import game.Game;
import game.object.CircleGameObject;
import game.object.Enemy;
import game.object.LinearBullet;
import game.ui.HealthBar;
import game.ui.UI;
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
				Game.addEnemyBullet(new EnemyBullet(px, py, Global.Dir.DOWN,
						Global.shooter_enemy_bullet_speed));
				EventHandler.add(new EnemyShooterBulletEvent(
						Global.shooter_enemy_bullet_delay));
			}
		}
	}

	public static class BossEnemy extends Enemy {
		HealthBar hpbar;
		boolean ready = false;
		{
			maxhp = 500;
			hp = maxhp;
			size = 24;
			speed = 0.1f;
		}

		public BossEnemy(float px, float py) {
			super(px, py);
			hpbar = new HealthBar(this);
		}

		public void spawn() {
			super.spawn();
			EventHandler.add(new EnemyBossBulletEvent1(4000));
			UI.addElement(hpbar);
		}

		@Override
		public void death() {
			super.death();
			UI.removeElement(hpbar);
		}

		@Override
		public void update() {
			super.update();
		}

		public void render() {
			super.render();
		}

		public void renderGlow() {
			super.render();
		}

		private static final float maxcount1 = 10;
		private static float count1 = maxcount1;
		private static final float maxcount2 = 80;
		private static float count2 = maxcount2;

		class EnemyBossBulletEvent1 extends EnemyBulletEvent {
			static final int rings = 24;

			public EnemyBossBulletEvent1(long time) {
				super(time);
			}

			@Override
			public void run() {
				if (hp <= 0)
					return;
				for (float i = 0; i < 360f; i += 360f / rings) {
					Game.addEnemyBullet(new EnemyBullet(px, py, (float) Math
							.toRadians(i + count1 * 360f / rings / maxcount1),
							Global.boss_enemy_bullet_speed));
				}
				if (count1 > 0) {
					EventHandler.add(new EnemyBossBulletEvent1(400));
					count1 -= 1;
				} else {
					count1 = maxcount1;
					EventHandler.add(new EnemyBossBulletEvent2(4000));
				}
			}
		}

		class EnemyBossBulletEvent2 extends EnemyBulletEvent {
			static final int rounds = 4;

			public EnemyBossBulletEvent2(long time) {
				super(time);
			}

			@Override
			public void run() {
				if (hp <= 0)
					return;
				Game.addEnemyBullet(new EnemyBullet(px, py, (float) Math
						.toRadians(361f * count2 / maxcount2 * 4),
						Global.boss_enemy_bullet_speed));
				Game.addEnemyBullet(new EnemyBullet(px, py, (float) Math
						.toRadians(361f * count2 / maxcount2 * 4),
						Global.boss_enemy_bullet_speed));

				if (count2 > 0) {
					EventHandler.add(new EnemyBossBulletEvent2(10));
					count2 -= 0.5f;
				} else {
					count2 = maxcount2;
					EventHandler.add(new EnemyBossBulletEvent1(4000));
				}
			}
		}
	}

	public static class ReturnBoss extends Enemy {

		public ReturnBoss(float px, float py) {
			super(px, py);
			hp = 500;
			size = 24;
		}

		public void spawn() {
			super.spawn();
			shoot();
		}

		private void shoot() {
			EventHandler.add(new ReturnBossBulletEvent(0,
					Global.return_boss_bullet_count, 0));
		}

		public class ReturnBossBullet extends EnemyBullet {

			public ReturnBossBullet(float px, float py, float dir, float speed) {
				super(px, py, dir, speed);
			}

			public void update() {
				super.update();
				accelerate(Global.return_boss_bullet_acc);
				if (this.getSpeed() < 0
						&& CircleGameObject.checkCollision(this,
								ReturnBoss.this)) {
					remove();
				}
			}
		}

		public class ReturnBossBulletEvent extends Event {
			int count;
			float ddir;

			public ReturnBossBulletEvent(long time, int count, float ddir) {
				super(time);
				this.count = count;
				this.ddir = ddir;
			}

			@Override
			public void run() {
				if (count < 0) {
					EventHandler.add(new Event(
							Global.return_boss_bullet_wave_delay) {
						@Override
						public void run() {
							shoot();
						}
					});
				} else {
					for (int i = 0; i < Global.return_boss_bullet_stream; i++) {
						Game.addEnemyBullet(new ReturnBossBullet(px, py,
								(float) (Math.PI * 2
										/ Global.return_boss_bullet_stream * i)
										+ ddir, Global.return_boss_bullet_speed));
					}
					EventHandler.add(new ReturnBossBulletEvent(
							Global.return_boss_bullet_delay, count - 1, ddir
									+ Global.return_boss_bullet_rotate));
				}
			}
		}

	}
}
