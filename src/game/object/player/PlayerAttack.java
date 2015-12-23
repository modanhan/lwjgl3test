package game.object.player;

import events.Event;
import events.EventHandler;
import game.Game;
import game.object.CircleGameObject;
import game.object.Laser;
import game.object.TrailVisual;
import game.object.player.Player.PlayerLinearBullet;

abstract class PlayerAttack {

	private boolean active = false;

	public void start() {
		active = true;
		init();
	}

	protected abstract void init();

	public void cancel() {
		active = false;
	}

	public class BulletAttackEvent extends Event {
		private CircleGameObject bullet;
		private CircleGameObject spawner;
		private float dx, dy;

		/**
		 * 
		 * @param delay
		 *            The amount of time to wait until the event triggers.
		 * @param bullet
		 *            The type of bullet fired.
		 * @param spawner
		 *            The object that does the attack.
		 * @param dx
		 *            Displacement x from the spawner.
		 * @param dy
		 *            Displacement y from the spawner.
		 */
		public BulletAttackEvent(int delay, CircleGameObject bullet,
				CircleGameObject spawner, float dx, float dy) {
			super(delay);
			this.bullet = bullet;
			this.spawner = spawner;
			this.dx = dx;
			this.dy = dy;
		}

		/**
		 * 
		 * @param delay
		 *            The amount of time to wait until the event triggers.
		 * @param bullet
		 *            The type of bullet fired.
		 * @param spawner
		 *            The object that does the attack.
		 */
		public BulletAttackEvent(int delay, PlayerLinearBullet bullet,
				CircleGameObject spawner) {
			this(delay, bullet, spawner, 0, 0);
		}

		@Override
		public void run() {
			if (!spawner.remove && active) {
				CircleGameObject cgo = bullet.clone();
				cgo.px = spawner.px + dx;
				cgo.py = spawner.py + dy;
				Game.addPlayerBullet(cgo);
				EventHandler.add(new BulletAttackEvent(getDelay(), bullet, spawner,dx, dy));
			}
		}
	}
	public class LaserAttackEvent extends Event{
		private Laser laser;
		private CircleGameObject spawner;

		public LaserAttackEvent(int delay, Laser laser, CircleGameObject spawner) {
			super(delay);
			this.laser = laser;
			this.spawner = spawner;
		}

		@Override
		public void run() {
			if (!spawner.remove && active) {
				Game.addPlayerLaser(laser);
			}
		}
	}
}