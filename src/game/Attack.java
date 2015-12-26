package game;

import game.object.Bullet;
import game.object.GameObject;

import java.util.ArrayList;
import util.Time;

/**
 * 
 * Defines an attack, has ability to fire bullets
 * from a game object at a fixed rate
 * and a initial wait time.
 *
 */
public abstract class Attack {
	private boolean active, friendly;
	private GameObject spawner;
	private ArrayList<Bullet> bullets;
	private ArrayList<Integer> delay;
	private ArrayList<Long> lastfiretime;

	/**
	 * Defines a attack, bullets will spawn at spawner. Bullets fired by players
	 * are friendly.
	 * @param spawner
	 * @param friendly
	 */
	public Attack(GameObject spawner, boolean friendly) {
		this.spawner = spawner;
		this.friendly = friendly;
		activate();
	}

	/**
	 * Defines how the attack is initialized.
	 */
	public abstract void init();

	public void update() {
		if (!spawner.remove && active) {
			long t = Time.getTime();
			for (int i = 0; i < bullets.size(); i++) {
				if (lastfiretime.get(i) + delay.get(i) <= t) {
					Bullet lb = bullets.get(i).clone();
					lb.x = spawner.x;
					lb.y = spawner.y;
					if (friendly)
						Game.addPlayerBullet(lb);
					else
						Game.addEnemyBullet(lb);
					lastfiretime.set(i, t);
				}
			}

		}
	}

	public void activate() {
		active = true;
		bullets = new ArrayList<Bullet>();
		lastfiretime = new ArrayList<Long>();
		delay = new ArrayList<Integer>();
		init();
	}

	public void cancel() {
		active = false;
	}

	/**
	 * Add a bullet with a cooldown.
	 * 
	 * @param bullet
	 * @param cooldown
	 */
	public void addBullet(Bullet bullet, int cooldown) {
		bullets.add(bullet);
		delay.add(cooldown);
		lastfiretime.add(Time.getTime());
	}

	/**
	 * Add a bullet with a cooldown and an initial wait time.
	 * 
	 * @param bullet
	 * @param cooldown
	 * @param wait
	 */
	public void addBullet(Bullet bullet, int cooldown, int wait) {
		bullets.add(bullet);
		delay.add(cooldown);
		lastfiretime.add(Time.getTime() + wait);
	}
}
