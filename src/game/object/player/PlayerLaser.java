package game.object.player;

import util.Global;
import game.Game;
import game.object.Bullet;
import game.object.visual.LaserFadeVisual;
import graphics.Texture;
import static org.lwjgl.opengl.GL11.*;

public class PlayerLaser extends Bullet {

	public PlayerLaser(float theta) {
		super(LINE);
		this.theta = theta;
	}

	@Override
	public Bullet clone() {
		return new PlayerLaser(theta);
	}

	@Override
	public void update() {
		kill();
	}

	@Override
	public void render() {
		Texture.bind(0);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glRotatef((float) Math.toDegrees(theta), 0, 0, 1);
		glBegin(GL_QUADS);

		glColor4f(1, 1, 1, 1);
		glTexCoord2f(0, 0);
		glVertex2f(0, 1);
		glTexCoord2f(0, 1);
		glVertex2f(0, -1);
		glTexCoord2f(1, 1);
		glVertex2f(Global.height + Global.width, 1);
		glTexCoord2f(1, 0);
		glVertex2f(Global.height + Global.width, -1);

		glEnd();
		glPopMatrix();
	}

	@Override
	public void death() {
		Game.visuals.add(new LaserFadeVisual(x, y, theta, 4.5f, 1000));
	}

	@Override
	public Bullet clone(float x, float y) {
		Bullet b = clone();
		b.x = x;
		b.y = y;
		return b;
	}

}
