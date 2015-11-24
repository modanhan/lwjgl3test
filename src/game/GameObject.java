package game;

import util.Time;

public abstract class GameObject implements Comparable<GameObject> {
	private long timecreated;

	public GameObject() {
		timecreated = Time.getTimeMillis();
	}

	public abstract void update();

	public abstract void render();

	// TODO this function is utter garbage/complete bs, someone do sth abt this
	public int compareTo(GameObject g) {
		if (equals(g))
			return 0;
		// seriously hashcode??
		return timecreated < g.timecreated ? 1 : (hashCode() < g.hashCode() ? 1
				: -1);
	}
}
