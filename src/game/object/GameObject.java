package game.object;

import util.Global;

/**
 * 
 * Defines the super class for all updating, rendering objects of the game. Has
 * an uuid, and functions to be removed from the game.
 *
 */
public abstract class GameObject implements Comparable<GameObject> {
	private long uuid;
	public boolean remove = false;
	public float x, y;

	public GameObject() {
		uuid = Global.random.nextLong();
	}

	public abstract void update();

	public abstract void render();

	public void renderGlow() {

	}

	/**
	 * Performs the action upon death WITHOUT removing the object. This method
	 * should not be called explicitly, unless for very specific reasons.
	 */
	public abstract void death();

	/**
	 * Kills the object, i.e. triggers death.
	 */
	public final void kill() {
		death();
		remove();
	}

	/**
	 * Removes the object without triggering death.
	 */
	public void remove() {
		remove = true;
	}

	@Override
	public final boolean equals(Object g) {
		return uuid == ((GameObject) g).uuid;
	}

	@Override
	public final int compareTo(GameObject g) {
		return Long.compare(this.uuid, g.uuid);
	}
}
