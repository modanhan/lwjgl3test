package game.object.player;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import util.Global;
import game.Game;
import game.object.Bullet;
import game.object.visual.ExplosionVisual;
import graphics.Graphics;
import graphics.Texture;

public class PlayerLinearBullet extends Bullet {

	public PlayerLinearBullet() {
		this(Global.player_bullet_speed, Global.Dir.UP);
	}

	public PlayerLinearBullet(float dir) {
		this(Global.player_bullet_speed, dir);
	}

	public PlayerLinearBullet(float speed, float dir) {
		super(CIRCLE);
		setSpeedDir(speed, dir);
		radius = Global.player_bullet_size;
	}

	@Override
	public Bullet clone() {
		return new PlayerLinearBullet(getSpeed(), getDir());
	}

	@Override
	public void render() {
		Texture.bind(Global.Textures.circle);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor3f(1, 1, 1);
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
