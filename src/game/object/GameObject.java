package game.object;

import global.Global;

/**
 * 
 * Defines the super class for all updating, rendering objects
 * of the game.
 *
 */
public abstract class GameObject implements Comparable<GameObject> {
	private long uuid;
	public boolean remove = false;

	public GameObject() {
		uuid = Global.random.nextLong();
	}

	public abstract void update();

	public abstract void render();
	
	/**
	 * Defines the events triggered upon death.
	 * This method should not be called explicitly,
	 * unless for very specific reasons.
	 */
	public abstract void death();
	
	/**
	 * Kills the object, i.e. triggers death.
	 */
	public final void kill(){
		death();
		remove();
	}

	/**
	 * Removes the object without triggering death.
	 */
	public final void remove() {
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
