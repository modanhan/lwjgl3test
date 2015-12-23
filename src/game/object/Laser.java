package game.object;

import static org.lwjgl.opengl.GL11.*;

import graphics.Graphics;
import graphics.Texture;
import util.Global;
import util.Time;

public class Laser extends GameObject {
	protected int damage = 1; //TODO global default var
	protected boolean damagetick = true;
	protected int delay = 500;//TODO global default var
	protected int time = 0;
	CircleGameObject parent;
	float dir;
	float size = 4;//TODO Global default var
	public Laser(CircleGameObject parent,float dir) {
		this.parent = parent;
		this.dir = dir;
	}

	@Override
	public void update() {
		if(parent.remove){
			this.kill();
		}
		damagetick = false;
		time+=Time.getDelta();
		if(time>=delay){
			damagetick = true;
			time=0;
		}
	}

	@Override
	public void render() {
		float x = parent.px;
		float y = parent.py;
		Graphics.set();
		Texture.bind(0);
		glPushMatrix();
		glTranslatef(x,y,0);
		glRotatef((float)Math.toDegrees(dir),0,0,1);
		glBegin(GL_QUADS);
		final float fade = 0.4f;
		if(time/(float)delay<fade){
			glColor3f(1,1,1);
			glTexCoord2f(0,0);glVertex2f(0,size*(1-time/(float)delay/fade));
			glTexCoord2f(0,1);glVertex2f(0,-size*(1-time/(float)delay/fade));
			glTexCoord2f(1,1);glVertex2f(Global.height+Global.width,size*(1-time/(float)delay/fade));
			glTexCoord2f(1,0);glVertex2f(Global.height+Global.width,-size*(1-time/(float)delay/fade));
		}else{
			glColor4f(1,1,1,0.2f);
			glTexCoord2f(0,0);glVertex2f(0,0.5f);
			glTexCoord2f(0,1);glVertex2f(0,-0.5f);
			glTexCoord2f(1,1);glVertex2f(Global.height+Global.width,0.5f);
			glTexCoord2f(1,0);glVertex2f(Global.height+Global.width,-0.5f);
		}
		glEnd();
		glPopMatrix();
		Graphics.reset();
	}
	public void renderGlow(){
		float x = parent.px;
		float y = parent.py;
		glPushMatrix();
		glTranslatef(x,y,0);
		glRotatef((float)Math.toDegrees(dir),0,0,1);
		glBegin(GL_QUADS);
		glColor3f(1,1,1);
		if(damagetick){
			glTexCoord2f(0,0);glVertex2f(0,size);
			glTexCoord2f(0,1);glVertex2f(0,-size);
			glTexCoord2f(1,1);glVertex2f(Global.height+Global.width,size);
			glTexCoord2f(1,0);glVertex2f(Global.height+Global.width,-size);
		}else{
			glTexCoord2f(0,0);glVertex2f(0,0.5f);
			glTexCoord2f(0,1);glVertex2f(0,-0.5f);
			glTexCoord2f(1,1);glVertex2f(Global.height+Global.width,0.5f);
			glTexCoord2f(1,0);glVertex2f(Global.height+Global.width,-0.5f);
		}
		glEnd();
		glPopMatrix();
	}
	@Override
	public void death() {

	}
	
	public int getDamage(){
		if(damagetick){
			return damage;
		}else{
			return 0;
		}
	}
	
	public static boolean checkCollision(Laser a, CircleGameObject b){
		float x = a.parent.px;
		float y = a.parent.py;
		float dir = a.dir;
		float bdir = (float) Math.atan2(b.py-y, b.px-x);
		float dist = (float) Math.hypot(b.py-y, b.px-x);
		if(Math.abs(Math.sin(dir-bdir))*dist<a.size+b.size){
			return true;
		}else{
			return false;
		}
	}
}
