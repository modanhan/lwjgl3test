package events;

import util.Time;

public abstract class Event implements Comparable<Event> {
	private long time;

	/**
	 * add this to EventHandler to trigger the event at time
	 * current time + parameter time
	 * 
	 * @param time
	 * the amount of time to wait until the event triggers
	 */
	public Event(long time) {
		this.time = Time.getTimeMillis() + time;
	}

	public int compareTo(Event e) {
		if (e.getTime() < time) {
			return 1;
		} else {
			return -1;
		}
	}

	public abstract void run();

	public long getTime() {
		return time;
	}
}
