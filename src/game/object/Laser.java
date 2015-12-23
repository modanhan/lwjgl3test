package game.object;

import static org.lwjgl.opengl.GL11.*;

import graphics.Graphics;
import graphics.Texture;
import util.Global;
import util.Time;

public class Laser extends GameObject {
	protected int damage = 10; // TODO global default var
	protected boolean damagetick = true;
	protected int delay = 1000;// TODO global default var
	protected int time = 0;
	CircleGameObject parent;
	float dir;
	
	//TODO check if you like this way of pulsing effect better, and implement renderGlow similarly if you do?
	float dsize = .75f, size;// TODO Global default var
	float r, g, b, a, dr, dg, db, da;

	public Laser(CircleGameObject parent, float dir) {
		this.parent = parent;
		this.dir = dir;
		dr = dg = db = 1f;
		da = .1f;
	}

	@Override
	public void update() {
		if (parent.remove) {
			this.kill();
		}
		damagetick = false;
		time += Time.getDelta();
		if (time >= delay) {
			damagetick = true;
			time = 0;
		}
		r += (dr - r) / 25f;
		g += (dg - g) / 25f;
		b += (db - b) / 25f;
		a += (da - a) / 25f;
		size += (dsize - size) / 10f;
		if (damagetick) {
			r = g = b = a = 1f;
			size = 4f;
		}
	}

	@Override
	public void render() {
		float x = parent.px;
		float y = parent.py;
		Graphics.set();
		Texture.bind(0);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glRotatef((float) Math.toDegrees(dir), 0, 0, 1);
		glBegin(GL_QUADS);
		
		glColor4f(r, g, b, a);
		glTexCoord2f(0, 0);
		glVertex2f(0, size);
		glTexCoord2f(0, 1);
		glVertex2f(0, -size);
		glTexCoord2f(1, 1);
		glVertex2f(Global.height + Global.width, size);
		glTexCoord2f(1, 0);
		glVertex2f(Global.height + Global.width, -size);
		
		glEnd();
		glPopMatrix();
		Graphics.reset();
	}

	public void renderGlow() {
		float x = parent.px;
		float y = parent.py;
		glPushMatrix();
		glTranslatef(x, y, 0);
		glRotatef((float) Math.toDegrees(dir), 0, 0, 1);
		glBegin(GL_QUADS);
		
		glColor4f(r, g, b, a);
		glTexCoord2f(0, 0);
		glVertex2f(0, size);
		glTexCoord2f(0, 1);
		glVertex2f(0, -size);
		glTexCoord2f(1, 1);
		glVertex2f(Global.height + Global.width, size);
		glTexCoord2f(1, 0);
		glVertex2f(Global.height + Global.width, -size);
		
		glEnd();
		glPopMatrix();
	}

	@Override
	public void death() {

	}

	public int getDamage() {
		if (damagetick) {
			return damage;
		} else {
			return 0;
		}
	}

	public static boolean checkCollision(Laser a, CircleGameObject b) {
		float x = a.parent.px;
		float y = a.parent.py;
		float dir = a.dir;
		float bdir = (float) Math.atan2(b.py - y, b.px - x);
		float dist = (float) Math.hypot(b.py - y, b.px - x);
		if (Math.abs(Math.sin(dir - bdir)) * dist < a.dsize + b.size) {
			if(Math.cos(dir - bdir)>0){
				return true;
			}else{
				return false;
			}
		} else {
			return false;
		}
	}
}
