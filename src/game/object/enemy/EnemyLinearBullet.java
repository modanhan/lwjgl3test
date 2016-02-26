package game.object.enemy;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import game.Game;
import game.object.Bullet;
import game.object.visual.ExplosionVisual;
import graphics.Graphics;
import graphics.Texture;
import util.Global;

public class EnemyLinearBullet extends Bullet {

	public EnemyLinearBullet() {
		super(CIRCLE);
		radius = 5;
	}
	
	public EnemyLinearBullet(float x, float y, float speed, float dir){
		super(CIRCLE, speed, dir);
		this.x=x;
		this.y=y;
	}

	public EnemyLinearBullet(float speed, float dir) {
		super(CIRCLE, speed, dir);
		radius = 5;
	}

	@Override
	public Bullet clone() {
		return new EnemyLinearBullet(getSpeed(), getDir());
	}

	@Override
	public void render() {
		Texture.bind(Global.Textures.circle);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor3f(1, 0.5f, 1);
		Graphics.quad(radius);
		glPopMatrix();
	}

	@Override
	public void death() {
		Game.addVisuals(new ExplosionVisual(x, y, radius, radius * 10, 1000));
	}

	@Override
	public void update() {
		updateLocation();
	}

	@Override
	public Bullet clone(float x, float y) {
		Bullet b = clone();
		b.x = x;
		b.y = y;
		return b;
	}


}
