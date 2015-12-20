package game.object.player;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;
import events.Event;
import events.EventHandler;
import game.Game;
import game.object.CircleGameObject;
import game.object.ExplosionVisual;
import game.object.LinearBullet;
import graphics.Graphics;
import util.Global;
import util.Keyboard;
import util.Time;

public class Player extends CircleGameObject {
	private final float SPEED = .3f;
	private final float SLOWSPEED = .1f;
	public int mode = 1;
	public int power = 1;
	int powerlevel = 0;

	private final PlayerAttack[] linearattacks = { new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_init_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_init_bullet_speed), Player.this));
		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this));
		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this, -5, 0));
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this, 5, 0));
		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this, -10, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this, 10, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this, -20, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this, 20, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .2f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .2f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .21f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .21f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .22f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .22f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .5f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .5f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .51f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .51f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .52f,
							Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .52f,
							Global.player_bullet_speed), Player.this, 0, 0));
		}
	}
	};

	PlayerAttack attack = linearattacks[0];

	public Player() {
		size = Global.player_size;
		px = Global.width / 2;
		py = Global.height / 2;
		shoot();
	}

	public void shoot() {
		attack.start();
	}

	/**
	 * Switches to a linear attack with specified powerlevel
	 * 
	 * @param powerlevel
	 */
	public void linearAttack(int powerlevel) {
		attack.cancel();
		attack = linearattacks[powerlevel];
		attack.start();
		this.powerlevel = powerlevel;
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
				linearAttack(powerlevel + 1);
			}
			if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_MINUS)) {
				power--; // decrease powerup level
				linearAttack(powerlevel - 1);
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
		Game.addVisuals(new ExplosionVisual(this.px, this.py, 0, 720, 2500));
	}

	/**
	 * A linear bullet spawned by the player.
	 * 
	 * @author Modan
	 *
	 */
	public class PlayerLinearBullet extends LinearBullet {

		/**
		 * Spawns at the player's location.
		 * 
		 * @param dir
		 *            direction
		 * @param speed
		 *            speed
		 */
		public PlayerLinearBullet(float dir, float speed) {
			super(Player.this.px, Player.this.py, dir, speed);
			size = Global.player_bullet_size;
		}

		/**
		 * Spawns at specified location px, py
		 * 
		 * @param px
		 *            x location
		 * @param py
		 *            y location
		 * @param dir
		 *            direction
		 * @param speed
		 *            speed
		 */
		public PlayerLinearBullet(float px, float py, float dir, float speed) {
			super(px, py, dir, speed);
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

		public void renderGlow() {
			render();
		};

		@Override
		public void death() {
			Game.addVisuals(new ExplosionVisual(this.px, this.py, 0, 100, 500,
					.5f));
		}

		/**
		 * 
		 * @param px
		 *            location x
		 * @param py
		 *            location y
		 * @return a new player linear bullet with the same behavior at the
		 *         specified location
		 */
		public PlayerLinearBullet clone(float px, float py) {
			return new PlayerLinearBullet(px, py, dir, getSpeed());
		}
	}

}
