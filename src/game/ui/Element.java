package game.ui;

import static org.lwjgl.opengl.GL11.*;

import graphics.Graphics;

public abstract class Element {
	public int x,y, width, height;
	public abstract void update();
	public void render(){
		glPushMatrix();
		glTranslatef(x,y,0);
		glColor3f(1, 1, 1);
		Graphics.quad(width, height);
		glPopMatrix();
	}
}
