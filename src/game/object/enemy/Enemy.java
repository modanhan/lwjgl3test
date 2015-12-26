package game.object.enemy;

import static org.lwjgl.opengl.GL11.*;
import game.Game;
import game.object.CollidingGameObject;
import graphics.Graphics;
import util.Time;

/**
 * 
 * Basic circle shaped enemy.
 *
 */
public class Enemy extends CollidingGameObject {
	protected boolean hit = false;
	public boolean boss;
	public int maxhp = 1;
	public int hp = maxhp;
	float r, g, b, a, dr, dg, db, da;
	protected float tx, ty, speed = 0.1f;

	/**
	 * 
	 * @param x
	 *            position x
	 * @param y
	 *            position y
	 */
	public Enemy(float x, float y) {
		super(CIRCLE);
		this.x = x;
		this.y = y;
		this.tx = x;
		this.ty = y;
		r = dr = 1f;
		g = dg = 0f;
		b = db = .5f;
		a = da = 1f;
		radius = 50;
		hp=100;
	}

	public void spawn() {
		Game.addEnemy(this);
	}

	public void death() {
	}

	@Override
	public void render() {

		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor4f(r, g, b, a);
		Graphics.quad(radius);
		glPopMatrix();
	}

	public void renderGlow() {
		render();
	}

	protected void move() {
		float d = Time.getDelta();
		float dist = (float) Math.hypot(ty - y, tx - x);
		if (dist > speed * d) {
			x += (tx - x) / dist * speed * d;
			y += (ty - y) / dist * speed * d;
		} else {
			x = tx;
			y = ty;
		}
	}

	public void update() {
		move();
		r += (dr - r) / 15f;
		g += (dg - g) / 15f;
		b += (db - b) / 15f;
		a += (da - a) / 15f;
		if (hit) {
			r = g = b = 1;
		}
		if (hp <= 0) {
			kill();
		}
		hit = false;
	}

	public void takeHit() {
		hp--;
		hit = true;
	}

	public void takeHit(int damage) {
		if (damage != 0) {
			hp -= damage;
			hit = true;
		}
	}
}
