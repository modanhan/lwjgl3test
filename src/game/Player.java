package game;

import java.util.Vector;

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
			Game.add(new PlayerBullet(px, py, -45));
			Game.add(new PlayerBullet(px, py, -30));
			Game.add(new PlayerBullet(px, py, -15));
			Game.add(new PlayerBullet(px, py));
			Game.add(new PlayerBullet(px, py, 15));
			Game.add(new PlayerBullet(px, py, 30));
			Game.add(new PlayerBullet(px, py, 45));
		}

	}

	class PlayerBullet extends GameObject {
		private final int L = 4;
		private final float SPEED = .2f;
		private float dx, dy;
		private float px, py;
		PlayerBullet(float px, float py) {
			this.px = px;
			this.py = py;
			this.dx=0;
			this.dy=SPEED;
		}
		PlayerBullet(float px, float py, float dir){
			this.px = px;
			this.py = py;
			this.dx=(float) (Math.sin(Math.toRadians(dir))*SPEED);
			this.dy=(float) (Math.cos(Math.toRadians(dir))*SPEED);
		}
		PlayerBullet(float px, float py, float dx, float dy) {
			this.px = px;
			this.py = py;
			this.dx=(float) (dx/Math.hypot(dx, dy)*SPEED);
			this.dy=(float) (dy/Math.hypot(dx, dy)*SPEED);
		}
		@Override
		public void update() {
			int d = Time.getDelta();
			px += d*dx;
			py += d*dy;
			if (py > GlobalVars.HEIGHT + 500) {
				Game.remove(this);
			}
		}

		@Override
		public void render() {
			GL11.glPushMatrix();
			GL11.glTranslatef(px, py, 0);
			GL11.glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			GL11.glColor3f(1, 1, 1);
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glVertex2f( - L,  - L);
			GL11.glVertex2f( - L,  + L);
			GL11.glVertex2f( + L,  + L);
			GL11.glVertex2f( + L,  - L);
			GL11.glEnd();
			GL11.glPopMatrix();
		}

	}

}
