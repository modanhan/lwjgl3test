package game.object;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import graphics.Graphics;

/**
 * 
 * The super class of all game object shaped a circle. This will also be the
 * super class of all objects we use in the small game. The 3 fields are px -
 * position x, py - position y, and size - radius.
 */
public abstract class CircleGameObject extends GameObject {
	public float px;
	public float py;
	public float size = 10;

	public static boolean checkCollision(float ax, float ay, float bx,
			float by, float ar, float br) {
		return (Math.hypot(bx - ax, by - ay) < ar + br);
	}

	public static boolean checkCollision(CircleGameObject a, CircleGameObject b) {
		return (Math.hypot(b.px - a.px, b.py - a.py) <= a.size + b.size);
	}

	public static float getDistance(CircleGameObject a, CircleGameObject b) {
		return (float) Math.hypot(b.px - a.px, b.py - a.py);
	}

	/**
	 * Basic render function to render a circle with specified size at location
	 * px, py
	 */
	public void render() {
		glPushMatrix();
		glTranslatef(px, py, 0);
		glColor3f(1, 1, 1);
		Graphics.quad(size);
		glPopMatrix();
	}
}
