package events;

import java.util.PriorityQueue;

import util.Time;

/**
 * 
 * Handles the game's events.
 *
 */
public class EventHandler {

	public static PriorityQueue<Event> pq;

	public static void init() {
		pq = new PriorityQueue<Event>();
	}

	public static void update() {
		long time = Time.getTime();
		while (!pq.isEmpty() && pq.peek().getTime() <= time) {
			pq.poll().conditionalRun();
		}
	}

	public static void add(Event e) {
		pq.add(e);
	}

	public static void clear() {
		pq.clear();
	}

}
