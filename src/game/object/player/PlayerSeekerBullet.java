package game.object.player;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.List;
import java.util.ListIterator;

import game.Game;
import game.object.Bullet;
import game.object.GameObject;
import game.object.enemy.Enemy;
import game.object.visual.ExplosionVisual;
import game.object.visual.TrailVisual;
import graphics.Graphics;
import util.Global;
import util.Time;

public class PlayerSeekerBullet extends Bullet {
	public List<Enemy> targets;

	private float time;

	/**
	 * 
	 * @param px
	 *            the starting position x
	 * @param py
	 *            the starting position y
	 * @param dir
	 *            the direction in radians, e.g. 0 = right, Math.PI/2 = up
	 * @param speed
	 *            the speed the bullet travels
	 * @return
	 */
	/*
	 * public PlayerSeekerBullet(float px, float py, float dir, float speed,
	 * List<Enemy> targets) { super(CIRCLE, dir, speed); this.targets = targets;
	 * x = px; y = py; time = 0; }
	 */

	public PlayerSeekerBullet(float dir) {
		super(CIRCLE, .5f, dir);
		targets = Game.enemies;
		time = 0;
		radius = 5;
		Game.addVisuals(new TrailVisual(this, 3, 1000));
	}

	public void track() {
		float d = Time.getDelta() / 1000f;
		time += d;
		GameObject target = null;
		float distance = Float.MAX_VALUE;
		ListIterator<Enemy> iter = targets.listIterator();
		while (iter.hasNext()) {
			GameObject g = iter.next();
			float dist = (float) Math.hypot(g.x - x, g.y - y);
			if (dist <= distance) {
				distance = dist;
				target = g;
			}
		}
		if (target != null && time < Global.seeker_bullet_targetting_time) {
			float tx = target.x;
			float ty = target.y;
			dx = dx * (Global.seeker_bullet_targetting_time - time)
					/ Global.seeker_bullet_targetting_time + (tx - x)
					/ distance * time / Global.seeker_bullet_targetting_time
					* Global.seeker_bullet_acceleration * d;
			dy = dy * (Global.seeker_bullet_targetting_time - time)
					/ Global.seeker_bullet_targetting_time + (ty - y)
					/ distance * time / Global.seeker_bullet_targetting_time
					* Global.seeker_bullet_acceleration * d;
			float speed = (float) Math.hypot(dx, dy);
			if (speed > Global.seeker_bullet_maximum_speed) {
				dx = dx * Global.seeker_bullet_maximum_speed / speed;
				dy = dy * Global.seeker_bullet_maximum_speed / speed;
			} else if (speed < Global.seeker_bullet_minimum_speed) {
				dx = dx * Global.seeker_bullet_minimum_speed / speed;
				dy = dy * Global.seeker_bullet_minimum_speed / speed;
			}
		}
	}

	public void render() {
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor3f(1, 1, 1);
		Graphics.quad(radius);
		glPopMatrix();
	}

	@Override
	public Bullet clone() {
		return new PlayerSeekerBullet(getDir());
	}

	@Override
	public void death() {
		Game.addVisuals(new ExplosionVisual(x, y, radius, radius * 5, 1000));
	}

	@Override
	public void update() {
		track();
		updateLocation();
	}
}