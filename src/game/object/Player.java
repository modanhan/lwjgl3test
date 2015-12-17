package game.object;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;

import events.Event;
import events.EventHandler;
import game.Game;
import graphics.Graphics;
import util.Global;
import util.Keyboard;
import util.Time;

public class Player extends CircleGameObject {
	private boolean alive;
	private final float SPEED = .3f;
	private final float SLOWSPEED = .1f;
	public int mode = 1;
	public int power = 1;

	public Player() {
		alive = true;
		size = 10;
		px = Global.width / 2;
		py = Global.height / 2;
		shoot();
	}

	public void shoot() {
		EventHandler.add(new PlayerBulletEvent(0));
	}

	@Override
	public void update() {
		float speed = SPEED;
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
			speed = SLOWSPEED;
		}
		int d = Time.getDelta();
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
			py += (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			py -= (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			px -= (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			px += (d * speed);
		}
		if (Global.cheats) {
			if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_EQUAL)) {
				power++; // increase powerup level
			}
			if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_MINUS)) {
				power--; // decrease powerup level
			}
		}
		if (px < 0)
			px = 0;
		if (py < 0)
			py = 0;
		if (px > Global.width)
			px = Global.width;
		if (py > Global.height)
			py = Global.height;
	}

	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(px, py, 0);
		glColor3f(1, 1, 1);
		Graphics.quad(size);
		if (power >= 8) {
			glPushMatrix();
			glTranslatef(size * 3f, 0, 0);
			Graphics.quad(size / 2);
			glPopMatrix();
			glPushMatrix();
			glTranslatef(-size * 3f, 0, 0);
			Graphics.quad(size / 2);
			glPopMatrix();
		}
		glPopMatrix();
	}

	public void renderGlow() {
		render();
	}

	public void death() {
		alive = false;
	}

	public class PlayerBullet extends LinearBullet {

		public PlayerBullet(float px, float py, float dir) {
			super(px, py, dir);
			speed = Global.player_bullet_speed;
			size = Global.player_bullet_size;
		}
		@Override
		public void render() {
			glPushMatrix();
			glTranslatef(px, py, 0);
			glColor3f(1, 1, 1);
			Graphics.quad(size);
			glPopMatrix();
		}

		@Override
		public void death() {
			
		}
	}
	
	public class PlayerBulletEvent extends Event {

		public PlayerBulletEvent(long time) {
			super(time);
		}

		@Override
		public void run() {
			if (Player.this.alive) {
				Game.addPlayerBullet(new PlayerBullet(Player.this.px, Player.this.py, Global.player_bullet_dir));
				EventHandler.add(new PlayerBulletEvent(Global.player_bullet_delay));
			}
		}
	}
}
