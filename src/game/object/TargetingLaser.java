package game.object;

import game.Game;

public class TargetingLaser extends Laser {

	private float default_dir;
	public TargetingLaser(CircleGameObject parent, float dir) {
		super(parent, dir);
		default_dir=dir;
		damage = 1;
	}

	public void update() {
		super.update();
		GameObject near = null;
		float d = Float.MAX_VALUE;
		for (GameObject o : Game.enemies) {
			float x = CircleGameObject
					.getDistance(parent, (CircleGameObject) o);
			if (x < d) {
				d = x;
				near = o;
			}
		}
		if (near != null) {
			dir = (float) Math.atan2(((CircleGameObject) near).py - parent.py,
					((CircleGameObject) near).px - parent.px);
		} else {
			dir = default_dir;
		}
	}
}
