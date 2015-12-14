package game.object;

public abstract class CircleGameObject extends GameObject {
	public float px;
	public float py;
	public float size = 10;

	public static boolean checkCollision(float ax, float ay, float bx, float by, float ar, float br) {
		if (Math.hypot(bx - ax, by - ay) < ar + br) {
			return true;
		}
		return false;
	}

	public static boolean checkCollision(CircleGameObject a, CircleGameObject b) {
		if (Math.hypot(b.px - a.px, b.py - a.py) <= a.size + b.size) {
			return true;
		}
		return false;
	}

	public static float getDistance(CircleGameObject a, CircleGameObject b) {
		return (float) Math.hypot(b.px - a.px, b.py - a.py);
	}

	public void death() {

	}
}
