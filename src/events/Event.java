package events;

import util.Time;

public abstract class Event implements Comparable<Event> {
	private long time;

	/**
	 * Add this to EventHandler to trigger the event at time
	 * current time (the time this object is created) + parameter time.
	 * 
	 * i.e., param time after this object is created,
	 * the event is triggered.
	 * 
	 * @param time
	 * The amount of time to wait until the event triggers.
	 */
	public Event(long time) {
		this.time = Time.getTime() + time;
	}

	public final int compareTo(Event e) {
		if (e.getTime() < time) {
			return 1;
		} else {
			return -1;
		}
	}

	public abstract void run();

	public final long getTime() {
		return time;
	}
}
