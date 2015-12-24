package events;

import util.Time;

public abstract class Event implements Comparable<Event> {
	private long time;
	private int delay;
	private boolean active;

	/**
	 * Add this to EventHandler to trigger the event at time current time (the
	 * time this object is created) + parameter delay.
	 * 
	 * i.e., the event is triggered after the amount of delay specified.
	 * 
	 * @param delay
	 *            The amount of time to wait until the event triggers.
	 */
	public Event(int delay) {
		this.time = Time.getTime() + delay;
		this.delay = delay;
		active = true;
	}

	public final int compareTo(Event e) {
		if (e.getTime() < time) {
			return 1;
		} else {
			return -1;
		}
	}

	public abstract void run();

	/**
	 * 
	 * @return the in game time the event should be triggered
	 */
	public final long getTime() {
		return time;
	}

	/**
	 * 
	 * @return the amount of time waited/delayed
	 */
	public final int getDelay() {
		return delay;
	}

	/**
	 * Cancel the event
	 */
	public final void cancel() {
		active = false;
	}

	public final boolean isActive() {
		return active;
	}

	public final void conditionalRun() {
		if (active)
			run();
	}
}
