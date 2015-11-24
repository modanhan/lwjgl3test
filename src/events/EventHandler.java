package events;

import java.util.PriorityQueue;

import util.Time;

public class EventHandler {

	public static PriorityQueue<Event> pq;

	public static void init() {
		pq = new PriorityQueue<Event>();
	}

	public static void update() {
		long time = Time.getTimeMillis();
		while (!pq.isEmpty() && pq.peek().getTime() <= time) {
			pq.poll().run();
		}
	}

	public static void add(Event e) {
		pq.add(e);
	}

	public static void clear() {
		pq.clear();
	}

}
