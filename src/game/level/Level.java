package game.level;


public abstract class Level {
	// TODO, get more levels done
	private static Level[] levels = { new TestLevel() };

	public abstract void init();

	public static void init(int i) {
		levels[i].init();
	}
}
