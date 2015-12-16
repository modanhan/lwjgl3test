package game.object;

import static org.lwjgl.opengl.GL11.*;

import java.util.ListIterator;

import events.Event;
import game.Game;
import graphics.Graphics;
import util.Global;
import util.Time;

public abstract class Enemy extends CircleGameObject {
	protected boolean hit = false;
	public int hp = 1;
	protected float tx, ty, speed = 0.1f;

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
		for (GameObject g : Game.playerbullets) {
			if (CircleGameObject.checkCollision(this, (CircleGameObject) g)) {
				hp--;
				hit = true;
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
		}
		if (hp <= 0) {
			kill();
		}
	}

	public class EnemyBulletEvent extends Event {

		public EnemyBulletEvent(long time) {
			super(time);
		}

		@Override
		public void run() {
			if (hp <= 0)
				return;
			Game.enemybullets.add(new EnemyBullet(px, py, 0, 1));
		}

	}

	public class EnemyBullet extends LinearBullet {

		public EnemyBullet(float px, float py, float dir, float speed) {
			super(px, py, dir, speed);
			size = Global.enemy_bullet_default_size;
		}

		@Override
		public void update() {
			super.update();
			if (Game.player != null) {
				if (checkCollision(this, Game.player)) {
					if (!(Global.godmode && Global.cheats)) {
						Game.player.kill();
					}
					this.kill();
				}
			}
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
	}

	public static class EnemySpawnEvent extends Event {
		Enemy e;

		public EnemySpawnEvent(long time, Enemy e) {
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

		public EnemyDespawnEvent(long time, Enemy e) {
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

		public EnemyMoveEvent(long time, Enemy e, float x, float y) {
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

}
