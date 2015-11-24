package game;

import java.util.Set;
import java.util.TreeSet;

public class Game {
	private static Set<GameObject> s, toremove;

	public static void init() {
		s = new TreeSet<GameObject>();
		toremove = new TreeSet<GameObject>();
	}

	public static void update() {
		for (GameObject g : toremove) {
			s.remove(g);
		}
		toremove.clear();
		for (GameObject g : s) {
			g.update();
		}
		for (GameObject g : s) {
			g.render();
		}
	}

	public static void add(GameObject e) {
		s.add(e);
	}

	public static void remove(GameObject e) {
		toremove.add(e);
	}
}
