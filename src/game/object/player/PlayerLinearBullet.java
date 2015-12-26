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

public class PlayerLinearBullet extends Bullet {

	public PlayerLinearBullet() {
		super(CIRCLE);
		setSpeedDir(Global.player_bullet_speed, Global.Dir.UP);
		radius = Global.player_bullet_size;
	}

	@Override
	public Bullet clone() {
		return new PlayerLinearBullet();
	}

	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor3f(1, 1, 1);
		Graphics.quad(radius);
		glPopMatrix();
	}

	@Override
	public void death() {
		Game.addVisuals(new ExplosionVisual(x, y, radius, radius * 5, 1000));
	}

}
