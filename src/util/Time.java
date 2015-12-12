package util;

public class Time {
	static long lastlooptime, lastfps, time;
	static int delta, fps, tfps;

	public static void init() {
		lastlooptime = System.nanoTime() / 1000000;
		lastfps = lastlooptime;
	}

	public static long getTime() {
		return time;
	}

	public static void update() {
		time = System.currentTimeMillis();
		updateDelta();
		updateFPS();
	}

	public static void updateDelta() {
		delta = (int) (time - lastlooptime);
		lastlooptime = time;
	}

	public static void updateFPS() {
		if (time - lastfps > 1000) {
			fps = tfps;
			tfps = 0;
			lastfps += 1000;
		}
		tfps++;
	}

	public static int getDelta() {
		return delta;
	}

	public static int getFPS() {
		return fps;
	}
}
