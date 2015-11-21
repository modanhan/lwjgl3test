package game;

public class ModeHandler {
	private static Mode m;

	public static void update() {
		m.update();
	}
	
	public static void init(){
		m=new StartMode();
	}

	public static void setMode(Mode m) {
		ModeHandler.m = m;
	}
}
