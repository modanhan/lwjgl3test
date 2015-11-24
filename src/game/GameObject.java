package game;

import util.Time;

public abstract class GameObject implements Comparable<GameObject> {
	private long timecreated;

	public GameObject() {
		timecreated = Time.getTimeMillis();
	}

	public abstract void update();

	public abstract void render();

	public int compareTo(GameObject g) {
		return timecreated < g.timecreated ? 1 : (hashCode() < g.hashCode() ? 1
				: 0);
	}
}
