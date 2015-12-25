package game;

import game.object.CircleGameObject;
import game.object.Enemy.EnemyBullet;
import game.object.Laser;
import game.object.LinearBullet;
import game.object.SeekerBullet;

import java.util.ArrayList;

import util.Time;

/**
 * 
 * @author Modan
 *
 */
public abstract class Attack {
	private boolean active;
	private CircleGameObject spawner;
	private ArrayList<LinearBullet> linearbullets;
	private ArrayList<SeekerBullet> seekerbullets;
	private ArrayList<Laser> lasers;
	private ArrayList<Integer> linearbulletdelay, seekerbulletdelay,
			lasersdelay;
	private ArrayList<Long> linearbulletlastfiretime, seekerbulletlastfiretime,
			laserlastfiretime;

	public Attack(CircleGameObject spawner) {
		this.spawner = spawner;
		activate();
		init();
	}

	/**
	 * Defines how the attack is initialized.
	 */
	public abstract void init();

	public void update() {
		if (!spawner.remove && active) {
			long t = Time.getTime();
			for (int i = 0; i < linearbullets.size(); i++) {
				if (linearbulletlastfiretime.get(i) + linearbulletdelay.get(i) <= t) {
					LinearBullet lb = (LinearBullet) linearbullets.get(i)
							.clone();
					lb.px = spawner.px;
					lb.py = spawner.py;
					Game.addEnemyBullet(lb);
					linearbulletlastfiretime.set(i, t);
				}
			}
			for (int i = 0; i < seekerbullets.size(); i++) {
				if (seekerbulletlastfiretime.get(i) + seekerbulletdelay.get(i) <= t) {

					SeekerBullet sb = (SeekerBullet) seekerbullets.get(i)
							.clone();
					sb.px = spawner.px;
					sb.py = spawner.py;
					Game.addEnemyBullet(sb);
					seekerbulletlastfiretime.set(i, t);
				}
			}
			//TODO implement new type of laser, then write this to work
/*			for (int i = 0; i < lasers.size(); i++) {
				if (laserlastfiretime.get(i) + lasersdelay.get(i) <= t) {
					Game.addEnemyLaser(lasers.get(i));
					laserlastfiretime.set(i, t);
				}
			}*/
		}
	}

	public void activate() {
		active = true;
		linearbullets = new ArrayList<LinearBullet>();
		seekerbullets = new ArrayList<SeekerBullet>();
		lasers = new ArrayList<Laser>();
		linearbulletlastfiretime = new ArrayList<Long>();
		seekerbulletlastfiretime = new ArrayList<Long>();
		laserlastfiretime = new ArrayList<Long>();
		linearbulletdelay = new ArrayList<Integer>();
		seekerbulletdelay = new ArrayList<Integer>();
		lasersdelay = new ArrayList<Integer>();
		init();
	}

	public void cancel() {
		active = false;
	}

	public void add(SeekerBullet bullet, int delay) {
		seekerbullets.add(bullet);
		seekerbulletdelay.add(delay);
		seekerbulletlastfiretime.add(Time.getTime());
	}

	public void add(LinearBullet bullet, int delay) {
		linearbullets.add(bullet);
		linearbulletdelay.add(delay);
		linearbulletlastfiretime.add(Time.getTime());
	}

	public void add(Laser laser, int delay) {
		lasers.add(laser);
		lasersdelay.add(delay);
		laserlastfiretime.add(Time.getTime());
	}
}
