package game.object.powerup;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import util.Global;
import util.Time;
import game.object.CollidingGameObject;
import graphics.Graphics;
import graphics.Texture;

/**
 * 
 * Circular object, constantly travels down, moves left and right.
 *
 */
public class Powerup extends CollidingGameObject {
	public final int LINEAR = 0, SEEKER = 1, LASER = 2;
	private int type;
	private float dir;
	private long lastswitchtime;
	private float ox;

	private float[] r = { 0.75f, 0, 0 }, g = { 0, 0.75f, 0 }, b = { 0, 0, 0.75f }, a = { 1,
			1, 1 };

	public Powerup(float x, float y) {
		super(CIRCLE);
		radius = Global.powerup_radius;
		dir = 0;
		ox = x;
		this.y = y;
		type = LINEAR;
		lastswitchtime = Time.getTime();
	}

	@Override
	public void update() {
		long t = Time.getTime();
		int delta = Time.getDelta();
		if (lastswitchtime + Global.powerup_switch_time <= t) {
			type = (type + 1) % 3;
			lastswitchtime = t;
		}
		dir += delta * Global.powerup_h_speed;
		x = (float) Math.cos(dir) * Global.powerup_h_dist + ox;
		y -= delta * Global.powerup_v_speed;
		if (y > Global.gameheight + Global.margin || y < -Global.margin
				|| x > Global.gamewidth + Global.margin || x < -Global.margin) {
			remove();
		}
	}

	@Override
	public void render() {
		Texture.bind(Global.Textures.circle);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor4f(r[type], g[type], b[type], a[type]);
		Graphics.quad(radius);
		glPopMatrix();
		switch(type){
			case LINEAR:
				Texture.bind(Global.Textures.linearicon);
				break;
			case SEEKER:
				Texture.bind(Global.Textures.seekericon);
				break;
			case LASER:
				Texture.bind(Global.Textures.lasericon);
				break;
		}
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor4f(1, 1, 1, 1);
		Graphics.quad(radius);
		glPopMatrix();
	}

	public int getType() {
		return type;
	}

	@Override
	public void death() {
		// TODO Auto-generated method stub

	}

}
