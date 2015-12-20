package game.object.player;

import events.Event;
import events.EventHandler;
import game.Game;
import game.object.CircleGameObject;
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

	public class AttackEvent extends Event {
		private PlayerLinearBullet bullet;
		private CircleGameObject spawner;
		private float dx, dy;

		/**
		 * 
		 * @param delay The amount of time to wait until the event triggers.
		 * @param bullet The type of bullet fired.
		 * @param spawner The object that does the attack.
		 * @param dx Displacement x from the spawner.
		 * @param dy Displacement y from the spawner.
		 */
		public AttackEvent(int delay, PlayerLinearBullet bullet,
				CircleGameObject spawner, float dx, float dy) {
			super(delay);
			this.bullet = bullet;
			this.spawner = spawner;
			this.dx = dx;
			this.dy = dy;
		}

		/**
		 * 
		 * @param delay The amount of time to wait until the event triggers.
		 * @param bullet The type of bullet fired.
		 * @param spawner The object that does the attack.
		 */
		public AttackEvent(int delay, PlayerLinearBullet bullet,
				CircleGameObject spawner) {
			this(delay, bullet, spawner, 0, 0);
		}

		@Override
		public void run() {
			if (!spawner.remove && active) {
				Game.addPlayerBullet(bullet.clone(spawner.px + dx, spawner.py
						+ dy));
				EventHandler.add(new AttackEvent(getDelay(), bullet, spawner,
						dx, dy));
			}
		}
	}
}