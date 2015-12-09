package game;

import java.util.Random;

public abstract class GameObject implements Comparable<GameObject> {
	private long uuid;
	public boolean remove = false;

	public GameObject() {
		uuid = new Random().nextLong();
	}

	public abstract void update();

	public abstract void render();

	public void remove() {
		remove = true;
	}

	@Override
	public boolean equals(Object g) {
		return uuid == ((GameObject) g).uuid;
	}

	@Override
	public int compareTo(GameObject g) {
		return Long.compare(this.uuid, g.uuid);
	}
}
