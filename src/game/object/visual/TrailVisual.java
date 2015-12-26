package game.object.visual;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import events.Event;
import events.EventHandler;
import game.object.GameObject;
import graphics.Graphics;
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
		glPushMatrix();
		glBegin(GL_QUAD_STRIP);
		while (iter.hasNext()) {
			TrailSection t = iter.next();
			if (fade) {
				glColor4f(r, g, b, a * (iter.nextIndex() - 1) / trail.size());
			} else {
				glColor4f(r, g, b, a);
			}
			if (trail.size() == 1) {
				float dx = size;
				float dy = size;
				if (shrink) {
					dx = dx * (iter.nextIndex() - 1) / trail.size();
					dy = dy * (iter.nextIndex() - 1) / trail.size();
				}
				glTexCoord2f(0, 0);
				glVertex2f(-dx + t.x, -dy + t.y);
				glTexCoord2f(0, 1);
				glVertex2f(+dx + t.x, -dy + t.y);
				glTexCoord2f(1, 0);
				glVertex2f(-dx + t.x, +dy + t.y);
				glTexCoord2f(1, 1);
				glVertex2f(+dx + t.x, +dy + t.y);
			} else if (iter.previousIndex() == 0) {
				TrailSection tn = iter.next();
				float ndist = (float) Math.hypot(tn.x - t.x, tn.y - t.y);
				float nx = (tn.x - t.x) * size;
				float ny = (tn.y - t.y) * size;
				if (shrink) {
					nx = nx * (iter.nextIndex() - 1) / trail.size();
					ny = ny * (iter.nextIndex() - 1) / trail.size();
				}
				iter.previous();
				glTexCoord2f(0, 0);
				glVertex2f((-nx - ny) / ndist + t.x, (-ny + nx) / ndist + t.y);
				glTexCoord2f(0, 1);
				glVertex2f((-nx + ny) / ndist + t.x, (-ny - nx) / ndist + t.y);
				glTexCoord2f(0.5f, 0);
				glVertex2f((-ny) / ndist + t.x, (-nx) / ndist + t.y);
				glTexCoord2f(0.5f, 1);
				glVertex2f((+ny) / ndist + t.x, (+nx) / ndist + t.y);

			} else if (!iter.hasNext()) {
				iter.previous();
				TrailSection tp = iter.previous();
				float pdist = (float) Math.hypot(tp.x - t.x, tp.y - t.y);
				float px = (tp.x - t.x) * size;
				float py = (tp.y - t.y) * size;
				iter.next();
				iter.next();
				if (shrink) {
					px = px * (iter.nextIndex() - 1) / trail.size();
					py = py * (iter.nextIndex() - 1) / trail.size();
				}
				glTexCoord2f(0.5f, 1);
				glVertex2f((+py) / pdist + t.x, (+px) / pdist + t.y);
				glTexCoord2f(0.5f, 0);
				glVertex2f((-py) / pdist + t.x, (-px) / pdist + t.y);
				glTexCoord2f(0, 1);
				glVertex2f((-px + py) / pdist + t.x, (-py - px) / pdist + t.y);
				glTexCoord2f(0, 0);
				glVertex2f((-px - py) / pdist + t.x, (-py + px) / pdist + t.y);
			} else {
				TrailSection tn = iter.next();
				iter.previous();
				TrailSection tp = iter.previous();
				iter.next();
				float dx = (tn.x + tp.x - 2 * t.x) / 2f;
				float dy = (tn.y + tp.y - 2 * t.y) / 2f;
				float ddist = (float) Math.hypot(dx, dy);
				float fx = dx / ddist * size;
				float fy = dy / ddist * size;
				if (shrink) {
					fx = fx * (iter.nextIndex() - 1) / trail.size();
					fy = fy * (iter.nextIndex() - 1) / trail.size();
				}
				glTexCoord2f(0.5f, 0);
				glVertex2f((-fy + t.x), (-fx + t.y));
				glTexCoord2f(0.5f, 1);
				glVertex2f((fy + t.x), (fx + t.y));
			}
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
