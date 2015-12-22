package game.object.player;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;
import events.Event;
import events.EventHandler;
import game.Game;
import game.object.CircleGameObject;
import game.object.ExplosionVisual;
import game.object.LinearBullet;
import game.object.SeekerBullet;
import game.object.TrailVisual;
import graphics.Graphics;
import util.Global;
import util.Keyboard;
import util.Time;

public class Player extends CircleGameObject {
	private final float SPEED = .3f;
	private final float SLOWSPEED = .1f;
	public int mode = 1;
	public int powerlevel = 0;
	ArrayList<SideShooter> sideshooters;
	/*
	 * public class TestAttack extends Event { public TestAttack(int i) {
	 * super(i); }
	 * 
	 * public void run() { Game.addPlayerBullet(); Game.addPlayerBullet(new
	 * PlayerSeekerBullet(px, py, Global.Dir.DOWN,
	 * Global.seeker_bullet_default_speed)); Game.addPlayerBullet(new
	 * PlayerSeekerBullet(px, py, Global.Dir.LEFT,
	 * Global.seeker_bullet_default_speed)); Game.addPlayerBullet(new
	 * PlayerSeekerBullet(px, py, Global.Dir.RIGHT,
	 * Global.seeker_bullet_default_speed));
	 * 
	 * EventHandler.add(new TestAttack(1000)); } }
	 */

	private final PlayerAttack[] linearattacks = { new PlayerAttack() {

		@Override
		public void init() {
			// EventHandler.add(new TestAttack(100));

		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this));
		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, -5, 0));
			EventHandler.add(new AttackEvent(Global.player_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, 5, 0));
		}
	}, new PlayerAttack() {

		@Override
		public void init() {
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, -10, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, 10, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .2f, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .2f, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .21f, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .21f, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .5f, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .5f, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP - .51f, Global.player_bullet_speed), Player.this, 0, 0));
			EventHandler.add(new AttackEvent(Global.player_wtf_bullet_delay,
					new PlayerLinearBullet(Global.Dir.UP + .51f, Global.player_bullet_speed), Player.this, 0, 0));

		}
	},
			/**
			 * Power level 5, has 3 rotating side shooters
			 */
			new PlayerAttack() {

				@Override
				protected void init() {
					int sideshooternum = 3;
					for (int i = 0; i < sideshooternum; i++) {
						sideshooters.add(
								new SideShooter(Player.this, 2.5f, 50, Global.Dir.PI2 / sideshooternum * i, .001f));
						EventHandler.add(new AttackEvent(Global.player_bullet_delay,
								new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed),
								sideshooters.get(i)));
					}
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerLinearBullet(Global.Dir.UP - .2f, Global.player_bullet_speed), Player.this, 0,
							0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerLinearBullet(Global.Dir.UP + .2f, Global.player_bullet_speed), Player.this, 0,
							0));

				}
			},
			/**
			 * power level 6, has side shooters, testing seekers
			 */
			new PlayerAttack() {

				@Override
				protected void init() {
					int sideshooternum = 5;
					for (int i = 0; i < sideshooternum; i++) {
						sideshooters.add(
								new SideShooter(Player.this, 2.5f, 50, Global.Dir.PI2 / sideshooternum * i, .001f));
						EventHandler.add(new AttackEvent(Global.player_bullet_delay,
								new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed),
								sideshooters.get(i)));
					}
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerSeekerBullet(px, py, Global.Dir.UP, Global.seeker_bullet_default_speed),
							Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerSeekerBullet(px, py, Global.Dir.LEFT, Global.seeker_bullet_default_speed),
							Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerSeekerBullet(px, py, Global.Dir.RIGHT, Global.seeker_bullet_default_speed),
							Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerSeekerBullet(px, py, Global.Dir.DOWN, Global.seeker_bullet_default_speed),
							Player.this, 0, 0));
				}
			},
			/**
			 * power level 7, testing seekers
			 */
			new PlayerAttack() {

				@Override
				protected void init() {
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerLinearBullet(Global.Dir.UP, Global.player_bullet_speed), Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerSeekerBullet(px, py, Global.Dir.UP - 1.2f, Global.seeker_bullet_default_speed),
							Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerSeekerBullet(px, py, Global.Dir.UP + 1.2f, Global.seeker_bullet_default_speed),
							Player.this, 0, 0));
					EventHandler.add(new AttackEvent(Global.player_bullet_delay,
							new PlayerSeekerBullet(px, py, Global.Dir.UP, Global.seeker_bullet_default_speed),
							Player.this, 0, 0));

				}
			} };

	PlayerAttack attack = linearattacks[0];

	public Player() {
		size = Global.player_size;
		px = Global.width / 2;
		py = Global.height / 2;
		sideshooters = new ArrayList<SideShooter>();
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
		powerlevel += linearattacks.length;
		powerlevel %= linearattacks.length;
		sideshooters.clear();
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
				linearAttack(powerlevel + 1);
			}
			if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_MINUS)) {
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

		for (SideShooter ss : sideshooters)
			ss.update();
	}

	@Override
	public void render() {

		glPushMatrix();
		glTranslatef(px, py, 0);
		glColor3f(1, 1, 1);
		Graphics.quad(size);
		glPopMatrix();

		for (SideShooter ss : sideshooters)
			ss.render();
	}

	public void renderGlow() {
		glPushMatrix();
		glTranslatef(px, py, 0);
		glColor4f(1, 1, 1, .95f);
		Graphics.quad(size * 2f);
		glPopMatrix();
	}

	@Override
	public void remove() {
		super.remove();
		for (SideShooter ss : sideshooters) {
			ss.remove();
		}
	}

	public void death() {
		Game.addVisuals(new ExplosionVisual(this.px, this.py, 0, 720, 2500));
	}

	@Override
	public Player clone() {
		// TODO Auto-generated method stub
		return null;
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
			Game.addVisuals(new ExplosionVisual(this.px, this.py, 0, 100, 500, .5f));
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

		@Override
		public PlayerLinearBullet clone() {
			return new PlayerLinearBullet(px, py, dir, getSpeed());
		}
	}

	public class PlayerSeekerBullet extends SeekerBullet {

		/**
		 * Spawns at the player's location.s
		 * 
		 * @param dir
		 *            direction
		 * @param speed
		 *            speed
		 */
		public PlayerSeekerBullet(float dir, float speed) {
			this(Player.this.px, Player.this.py, dir, speed);
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
		public PlayerSeekerBullet(float px, float py, float dir, float speed) {
			super(px, py, dir, speed, Game.enemies);
			size = Global.player_bullet_size;
			Game.addVisuals(new TrailVisual(this, 1, 1, 1, 0.5f, this.size / 2f, 1000));
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
			Game.addVisuals(new ExplosionVisual(this.px, this.py, 0, 100, 500, .5f));
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
		public PlayerSeekerBullet clone(float px, float py) {
			return new PlayerSeekerBullet(px, py, dir, getSpeed());
		}

		@Override
		public PlayerSeekerBullet clone() {
			return new PlayerSeekerBullet(px, py, dir, getSpeed());
		}
	}

}
