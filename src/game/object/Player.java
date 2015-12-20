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
	int powerlevel = 0;

	private final PlayerAttack[] linearattacks = { new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_init_bullet_delay,
					Global.Dir.UP, Global.player_init_bullet_speed));
		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					Global.Dir.UP, Global.player_bullet_speed));
		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					Global.Dir.UP, Global.player_bullet_speed));
		}
	} };

	public Player() {
		alive = true;
		size = Global.player_size;
		px = Global.width / 2;
		py = Global.height / 2;
		shoot();
	}

	public void shoot() {
		linearattacks[0].start();
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
		alive = false;
		Game.addVisuals(new ExplosionVisual(this.px, this.py, 0, 720, 2500));
	}

	public void linearAttack(int powerlevel) {
		linearattacks[this.powerlevel].cancel();
		this.powerlevel = powerlevel;
		linearattacks[this.powerlevel].start();
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
	}

	private abstract class PlayerAttack {
		private boolean active = false;

		public void start() {
			active = true;
			init();
		}

		public abstract void init();

		public void cancel() {
			active = false;
		}

		public boolean isActive() {
			return active;
		}

		public class AttackEvent extends Event {
			float dir, speed;

			public AttackEvent(int time, float dir, float speed) {
				super(time);
				this.dir = dir;
				this.speed = speed;
			}

			@Override
			public void run() {
				if (Player.this.alive && PlayerAttack.this.isActive()) {
					Game.addPlayerBullet(new PlayerLinearBullet(dir, speed));
					EventHandler.add(new AttackEvent(getDelay(), dir, speed));
				}
			}
		}
	}
}
