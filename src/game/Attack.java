package game;

import game.object.Bullet;
import game.object.GameObject;

import java.util.ArrayList;
import util.Time;

/**
 * 
 * Defines an attack, has ability to fire bullets from a game object at a fixed
 * rate and a initial wait time.
 * 
 * For the attack to work, activate() must be called.
 * 
 * This class is updated per frame.
 *
 */
public abstract class Attack {
	private boolean active, friendly;
	private GameObject spawner;
	private ArrayList<Bullet> bullets;
	private ArrayList<Integer> delay;
	private ArrayList<Long> lastfiretime;
	private ArrayList<Float> offsetx, offsety;

	/**
	 * Defines a attack, bullets will spawn at spawner. Bullets fired by players
	 * are friendly.
	 * 
	 * @param spawner
	 * @param friendly
	 */
	public Attack(GameObject spawner, boolean friendly) {
		this.spawner = spawner;
		this.friendly = friendly;
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
					Bullet lb = bullets.get(i).clone(
							spawner.x + offsetx.get(i),
							spawner.y + offsety.get(i));
					if(lb == null)continue;
					if (friendly)
						Game.addPlayerBullet(lb);
					else
						Game.addEnemyBullet(lb);
					lastfiretime.set(i, t);
				}
			}

		}
	}

	/**
	 * Activates the attack, must be called for it to update.
	 */
	public void activate() {
		bullets = new ArrayList<Bullet>();
		lastfiretime = new ArrayList<Long>();
		delay = new ArrayList<Integer>();
		offsetx = new ArrayList<Float>();
		offsety = new ArrayList<Float>();
		init();
		active = true;
	}

	/**
	 * Cancels the attack, the opposite of activate.
	 */
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
		addBullet(bullet, cooldown, 0, 0, 0);
	}

	/**
	 * Add a bullet with a cooldown and an initial wait time.
	 * 
	 * @param bullet
	 * @param cooldown
	 * @param wait
	 */
	public void addBullet(Bullet bullet, int cooldown, int wait) {
		addBullet(bullet, cooldown, wait, 0, 0);
	}

	public void addBullet(Bullet bullet, int cooldown, float x, float y) {
		addBullet(bullet, cooldown, 0, x, y);
	}

	public void addBullet(Bullet bullet, int cooldown, int wait, float x,
			float y) {
		bullets.add(bullet);
		delay.add(cooldown);
		lastfiretime.add(Time.getTime() + wait);
		offsetx.add(x);
		offsety.add(y);
	}
}
