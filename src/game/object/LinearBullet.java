package game.object;

import util.Global;
import util.Time;

public abstract class LinearBullet extends CircleGameObject {
	public float dx, dy, speed, dir;

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
		this.speed = speed;
		dx = (float) Math.cos(dir) * speed;
		dy = (float) Math.sin(dir) * speed;
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
		if (py > Global.height + Global.margin || py < -Global.margin || px > Global.width + Global.margin
				|| px < -Global.margin) {
			remove();
		}
	}

	public void update() {
		move();
		checkPosition();
	}

}
