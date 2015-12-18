package game.object;

import util.Global;
import util.Time;

/**
 * 
 * Bullet that travels in a line.
 *
 */
public abstract class LinearBullet extends CircleGameObject {
	private float speed;
	public float dx, dy, dir;

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
	public LinearBullet(float px, float py, float dir, float speed) {
		this.px = px;
		this.py = py;
		this.dir = dir;
		this.setSpeed(speed);
		dx = (float) Math.cos(dir) * speed;
		dy = (float) Math.sin(dir) * speed;
	}

	/**
	 * change the speed
	 * 
	 * @param speed
	 *            the speed to set to
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
		dx = (float) Math.cos(dir) * speed;
		dy = (float) Math.sin(dir) * speed;
	}

	/**
	 * Change the speed by a
	 * 
	 * @param a
	 *            the amount to change the speed
	 */
	public void accelerate(float a) {
		setSpeed(getSpeed() + a * Time.getDelta());
		setSpeed(getSpeed());
	}

	/**
	 * 
	 * @param px
	 *            the starting position x
	 * @param py
	 *            the starting position y
	 * @param dir
	 *            the direction in radians, e.g. 0 = right, Math.PI/2 = up
	 */
	public LinearBullet(float px, float py, float dir) {
		this(px, py, dir, Global.linear_bullet_default_speed);
	}

	public void move() {
		int d = Time.getDelta();
		px += d * dx;
		py += d * dy;
	}

	public void checkPosition() {
		if (py > Global.height + Global.margin || py < -Global.margin
				|| px > Global.width + Global.margin || px < -Global.margin) {
			remove();
		}
	}

	public void update() {
		move();
		checkPosition();
	}

	public float getSpeed() {
		return speed;
	}

}
