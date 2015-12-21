package game.object;

import static org.lwjgl.opengl.GL11.*;
import events.Event;
import game.Game;
import graphics.Graphics;
import util.Global;
import util.Time;

/**
 * 
 * Basic circle shaped enemy.
 *
 */
public abstract class Enemy extends CircleGameObject {
	protected boolean hit = false;
	public boolean boss;
	public int maxhp = 1;
	public int hp = maxhp;
	protected float tx, ty, speed = 0.1f;

	/**
	 * 
	 * @param px
	 *            position x
	 * @param py
	 *            position y
	 */
	public Enemy(float px, float py) {
		this.px = px;
		this.py = py;
		this.tx = px;
		this.ty = py;
	}

	public void spawn() {
		Game.addEnemy(this);
	}

	public void death() {
	}

	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(px, py, 0);
		if (!hit) {
			glColor3f(0, 0, 1);
		} else {
			glColor3f(1, 1, 1);
		}
		Graphics.quad(size);
		glPopMatrix();
	}

	public void renderGlow() {
		render();
	}

	protected void move() {
		float d = Time.getDelta();
		float dist = (float) Math.hypot(ty - py, tx - px);
		if (dist > speed * d) {
			px += (tx - px) / dist * speed * d;
			py += (ty - py) / dist * speed * d;
		} else {
			px = tx;
			py = ty;
		}
	}

	public void update() {
		move();
		hit = false;
		/*
		 * PlayerBullet b = null; ListIterator<GameObject> i =
		 * Game.playerbullets.listIterator(); while (i.hasNext()){ b =
		 * (PlayerBullet) i.next(); if(CircleGameObject.checkCollision(this,
		 * b)){ b.remove(); i.remove(); hp-=b.power; hit=true; } }
		 */
	/*	for (GameObject g : Game.playerbullets) {
			if (CircleGameObject.checkCollision(this, (CircleGameObject) g)) {
				hp--;
				hit = true;
				g.kill();
			}
		}
		if (Game.player != null) {
			if (CircleGameObject.checkCollision(this, Game.player)) {
				if (!(Global.godmode && Global.cheats)) {
					Game.player.kill();
				}
				hp--;
				hit = true;
			}
		}*/
		if (hp <= 0) {
			kill();
		}
	}
	
	public void takeHit(){
		hp--;
		hit=true;
	}

	public class EnemyBulletEvent extends Event {

		public EnemyBulletEvent(int time) {
			super(time);
		}

		@Override
		public void run() {
			if (hp <= 0)
				return;
			Game.enemybullets.add(new EnemyBullet(px, py, 0, 1));
		}

	}

	/**
	 * Basic enemy bullet, travels in a line.
	 */
	public class EnemyBullet extends LinearBullet {
		/**
		 * 
		 * @param px
		 *            the starting position x
		 * @param py
		 *            the starting position y
		 * @param dir
		 *            the direction in radians, e.g. 0 = right, Math.PI/2 = up
		 * @param speed
		 *            the speed the bullet travels
		 */
		public EnemyBullet(float px, float py, float dir, float speed) {
			super(px, py, dir, speed);
			size = Global.enemy_bullet_default_size;
		}

		@Override
		public void render() {
			glPushMatrix();
			glTranslatef(px, py, 0);
			glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			glColor3f(0, 0, 1);
			Graphics.quad(size);
			glPopMatrix();
		}

		public void renderGlow() {
			glPushMatrix();
			glTranslatef(px, py, 0);
			glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			glColor3f(0.5f, 0.5f, 1);
			Graphics.quad(size);
			glPopMatrix();
		}

		@Override
		public void death() {

		}

		@Override
		public CircleGameObject clone() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * 
	 * Event spawns an enemy.
	 *
	 */
	public static class EnemySpawnEvent extends Event {
		Enemy e;

		/**
		 * 
		 * @param time amount of time to wait to spawn enemy e
		 * @param e the enemy to spawn
		 */
		public EnemySpawnEvent(int time, Enemy e) {
			super(time);
			this.e = e;
		}

		@Override
		public void run() {
			e.spawn();
		}

	}

	public static class EnemyDespawnEvent extends Event {
		Enemy e;

		public EnemyDespawnEvent(int time, Enemy e) {
			super(time);
			this.e = e;
		}

		@Override
		public void run() {
			e.remove();
		}

	}

	public static class EnemyMoveEvent extends Event {
		Enemy e;
		float x, y;

		public EnemyMoveEvent(int time, Enemy e, float x, float y) {
			super(time);
			this.e = e;
			this.x = x;
			this.y = y;
		}

		@Override
		public void run() {
			e.tx = x;
			e.ty = y;
		}

	}
	
	@Override
	public CircleGameObject clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
