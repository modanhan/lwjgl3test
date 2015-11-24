package game;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import events.Event;
import events.EventHandler;
import util.Keyboard;
import util.Time;

public class Player extends GameObject {
	private final int L = 10;
	private final float SPEED = .3f;
	private int px, py;

	public Player() {
		px = GlobalVars.WIDTH / 2;
		py = GlobalVars.HEIGHT / 2;
		EventHandler.add(new PlayerBulletEvent(200));
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			System.out.println("alive");
		}
		int d = Time.getDelta();
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
			py += (int) (d * SPEED);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			py -= (int) (d * SPEED);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			px -= (int) (d * SPEED);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			px += (int) (d * SPEED);
		}
	}

	@Override
	public void render() {
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(px - L, py - L);
		GL11.glVertex2f(px - L, py + L);
		GL11.glVertex2f(px + L, py + L);
		GL11.glVertex2f(px + L, py - L);
		GL11.glEnd();
	}

	class PlayerBulletEvent extends Event {

		public PlayerBulletEvent(long time) {
			super(time);
		}

		@Override
		public void run() {
			EventHandler.add(new PlayerBulletEvent(100));
			Game.add(new PlayerBullet(px, py));
		}

	}

	class PlayerBullet extends GameObject {
		private final int L = 2;
		private final float SPEED = .6f;

		private int px, py;

		PlayerBullet(int px, int py) {
			this.px = px;
			this.py = py;
		}

		@Override
		public void update() {
			int d = Time.getDelta();
			py += (int) (SPEED * d);
			if (py > GlobalVars.HEIGHT + 500) {
				Game.remove(this);
			}
		}

		@Override
		public void render() {
			GL11.glColor3f(1, 1, 1);
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glVertex2f(px - L, py - L);
			GL11.glVertex2f(px - L, py + L);
			GL11.glVertex2f(px + L, py + L);
			GL11.glVertex2f(px + L, py - L);
			GL11.glEnd();
		}

	}

}
