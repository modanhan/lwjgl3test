package game.object;

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
	 * Updates location.
	 */
	@Override
	public void update() {
		int delta = Time.getDelta();
		x += dx * delta;
		y += dy * delta;
	}

	public abstract Bullet clone();
}
