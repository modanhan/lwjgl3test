package game.object;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.opengl.GL11;
import events.Event;
import events.EventHandler;
import graphics.Graphics;

public class ExplosionVisual extends GameObject {

	int duration;
	float px, py, ir, or;
	float r, g, b, a;

	public ExplosionVisual(float px, float py, float ir, float or,
			int duration, float r, float g, float b, float a) {
		this.duration = duration;
		this.px = px;
		this.py = py;
		this.ir = ir;
		this.or = or;
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
		EventHandler.add(new Event(duration) {

			@Override
			public void run() {
				remove();
			}
		});
	}

	public ExplosionVisual(float px, float py, float ir, float or,
			int duration, float a) {
		this(px, py, ir, or, duration, 1, 1, 1, a);
	}

	public ExplosionVisual(float px, float py, float ir, float or, int duration) {
		this(px, py, ir, or, duration, 1);
	}

	@Override
	public void update() {
		ir += (or - ir) / (8 + (duration / 500));
		a /= 1 + 75 / (float) (duration);
	}

	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(px, py, 0);
		GL11.glColor4f(r, g, b, a);
		Graphics.quad(ir);
		glPopMatrix();
	}

	@Override
	public void death() {
		// TODO Auto-generated method stub

	}

}
