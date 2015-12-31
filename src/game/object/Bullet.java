package game.object;

import util.Global;
import util.Time;

/**
 * A bullet, defined as a colliding game object with abilities to move.
 * 
 */
public abstract class Bullet extends CollidingGameObject {
	private float speed, dir;
	public float dx, dy;

	public Bullet(int shape) {
		super(shape);
	}

	public Bullet(int shape, float speed, float dir) {
		super(shape);
		setSpeedDir(speed, dir);
	}

	public void setSpeedDir(float speed, float dir) {
		this.speed = speed;
		this.dir = dir;
		calcDxDy();
	}

	public void setSpeed(float speed) {
		this.speed = speed;
		calcDxDy();
	}

	public void setDir(float dir) {
		this.dir = dir;
		calcDxDy();
	}

	private void calcDxDy() {
		dx = (float) (Math.cos(dir) * speed);
		dy = (float) (Math.sin(dir) * speed);
	}

	/**
	 * Updates location and removes this object if out of bounds.
	 */
	public void updateLocation() {
		int delta = Time.getDelta();
		x += dx * delta;
		y += dy * delta;
		if (y > Global.gameheight + Global.margin || y < -Global.margin
				|| x > Global.gamewidth + Global.margin || x < -Global.margin) {
			remove();
		}
	}

	public float getSpeed() {
		return speed;
	}

	public float getDir() {
		return dir;
	}

	public abstract Bullet clone();

	public abstract Bullet clone(float x, float y);
}
