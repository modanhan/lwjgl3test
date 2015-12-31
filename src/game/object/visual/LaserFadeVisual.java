package game.object.visual;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import util.Global;
import events.Event;
import events.EventHandler;
import game.object.GameObject;
import graphics.Graphics;
import graphics.Texture;

public class LaserFadeVisual extends GameObject {

	float theta, width;
	int duration;
	float a;

	public LaserFadeVisual(float x, float y, float theta, float width,
			int duration) {
		this.x = x;
		this.y = y;
		this.theta = theta;
		this.width = width;
		this.duration = duration;
		a = 1;
		EventHandler.add(new Event(duration) {

			@Override
			public void run() {
				kill();
			}

		});
	}

	@Override
	public void update() {
		width *= .95f;
		a *= .95f;
	}

	@Override
	public void render() {
		Texture.bind(0);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glRotatef((float) Math.toDegrees(theta), 0, 0, 1);
		glBegin(GL_QUADS);

		glColor4f(1, 1, 1, a);
		glTexCoord2f(0, 0);
		glVertex2f(0, width);
		glTexCoord2f(0, 1);
		glVertex2f(0, -width);
		glTexCoord2f(1, 1);
		glVertex2f(Global.height + Global.width, -0);
		glTexCoord2f(1, 0);
		glVertex2f(Global.height + Global.width, 0);

		glEnd();
		glPopMatrix();
	}

	@Override
	public void death() {
		// TODO Auto-generated method stub

	}

}
