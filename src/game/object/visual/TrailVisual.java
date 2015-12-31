package game.object.visual;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import events.Event;
import events.EventHandler;
import game.object.GameObject;
import graphics.Graphics;
import graphics.Texture;
import util.Global;
import util.Time;
import static org.lwjgl.opengl.GL11.*;

public class TrailVisual extends GameObject {
	GameObject target;
	float r, g, b, a, size;
	int duration;
	boolean shrink, fade;
	private List<TrailSection> trail = new LinkedList<TrailSection>();

	public TrailVisual(GameObject target, float r, float g, float b, float a,
			float size, int duration, boolean shrink, boolean fade) {
		this.target = target;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.size = size;
		this.duration = duration;
		this.shrink = shrink;
		this.fade = fade;

	}

	public TrailVisual(GameObject target, float r, float g, float b, float a,
			float size, int duration) {
		this(target, r, g, b, a, size, duration, true, true);
	}

	public TrailVisual(GameObject target, float size, int duration) {
		this(target, 1, 1, 1, 1, size, duration, true, true);
	}

	@Override
	public void update() {
		if (target.remove) {
			EventHandler.add(new Event(duration) {
				@Override
				public void run() {
					remove();
				}
			});
		} else {
			trail.add(new TrailSection(target.x, target.y, duration));
		}
		ListIterator<TrailSection> iter = trail.listIterator();
		while (iter.hasNext()) {
			TrailSection t = iter.next();
			if (t.time < 0) {
				iter.remove();
			} else {
				t.update();
			}

		}
	}

	public void render() {
		ListIterator<TrailSection> iter = trail.listIterator();
		Texture.bind(Global.Textures.circle);
		glPushMatrix();
		glBegin(GL_QUAD_STRIP);
		TrailSection prevT = null;
		while (iter.hasNext()) {
			TrailSection t = iter.next();
			if (fade) {
				glColor4f(r, g, b, a * (iter.nextIndex() - 1) / trail.size());
			} else {
				glColor4f(r, g, b, a);
			}
			float r = size; // radius the trail should be
			if (shrink) {
				// scale the radius so it gets smaller
				r *= (float)(iter.nextIndex() - 1) / trail.size();
			}
			if (prevT != null) {
				// get distance to previous point on trail
				float rx = prevT.x-t.x, ry = prevT.y-t.y;
				// scale the distance to correct radius
				float rd = (float) Math.hypot(rx, ry);
				rx *= r/rd;
				ry *= r/rd;
				glTexCoord2f(0.5f, 0);
				glVertex2f(ry + t.x, -rx + t.y);
				glTexCoord2f(0.5f, 1);
				glVertex2f(-ry + t.x, rx + t.y);
			}
			prevT = t;
		}
		glEnd();
		glPopMatrix();
	}

	public void renderAlternate() {
		ListIterator<TrailSection> iter = trail.listIterator();
		while (iter.hasNext()) {
			TrailSection t = iter.next();
			glPushMatrix();
			glTranslatef(t.x, t.y, 0);
			glBegin(GL_QUADS);
			if (fade) {
				glColor4f(r, g, b, a * (iter.nextIndex() - 1) / trail.size());
			} else {
				glColor4f(r, g, b, a);
			}
			if (shrink) {
				Graphics.quad(size * (iter.nextIndex() - 1) / trail.size());
			} else {
				Graphics.quad(size);
			}
			glEnd();
			glPopMatrix();
		}

	}

	@Override
	public void death() {
		// TODO Auto-generated method stub

	}

	static class TrailSection {
		float x, y;
		float time;

		public TrailSection(float x, float y, float duration) {
			this.x = x;
			this.y = y;
			this.time = duration;
		}

		public void update() {
			time -= Time.getDelta();
		}

	}

}
