package game.ui;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import game.object.enemy.Enemy;
import graphics.Graphics;
import util.Global;

public class HealthBar extends Element {
	Enemy enemy;
	public HealthBar(Enemy e){
		this.enemy = e;
	}
	@Override
	public void update() {
		
	}
	public void render(){
		glPushMatrix();
		final int BARWIDTH = 1000;
		final int BARHEIGHT = 40;
		final int BARMARGIN = 5;
		Graphics.set();
		glBindTexture(GL_TEXTURE_2D, 0);
		glTranslatef(Global.width/2, Global.height-BARHEIGHT*2, 0);
		glColor3f(0.5f,0.5f,0.5f);
		glBegin(GL_TRIANGLE_FAN);
		glTexCoord2f(0,0); glVertex2f( - BARWIDTH/2 - BARMARGIN,  - BARHEIGHT/2 - BARMARGIN);
		glTexCoord2f(0,1); glVertex2f( - BARWIDTH/2 - BARMARGIN,  + BARHEIGHT/2 + BARMARGIN);
		glTexCoord2f(1,1); glVertex2f( + BARWIDTH/2 + BARMARGIN,  + BARHEIGHT/2 + BARMARGIN);
		glTexCoord2f(1,0); glVertex2f( + BARWIDTH/2 + BARMARGIN,  - BARHEIGHT/2 - BARMARGIN);
		glEnd();
		glColor3f(0, 0, 1);
		glBegin(GL_TRIANGLE_FAN);
		glTexCoord2f(0,0); glVertex2f( - BARWIDTH/2,  - BARHEIGHT/2);
		glTexCoord2f(0,1); glVertex2f( - BARWIDTH/2,  + BARHEIGHT/2);
		glTexCoord2f(1,1); glVertex2f( - BARWIDTH/2+(enemy.hp/(float)enemy.maxhp*BARWIDTH),  + BARHEIGHT/2);
		glTexCoord2f(1,0); glVertex2f( - BARWIDTH/2+(enemy.hp/(float)enemy.maxhp*BARWIDTH),  - BARHEIGHT/2);
		glEnd();
		glPopMatrix();
		Graphics.reset();
	}

}
