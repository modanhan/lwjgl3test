package game.object;

/**
 * 
 * Defines the super class for all game objects that can interact with each
 * other. Currently has types line and circle.
 *
 */
public abstract class CollidingGameObject extends GameObject {
	public final static int LINE = 0, CIRCLE = 1;
	private int shape;
	public float radius, theta;

	public CollidingGameObject(int shape) {
		super();
		this.shape = shape;
	}

	public int getShape() {
		return shape;
	}
}
