package game.ui;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import graphics.Graphics;
import util.Global;

public class Sidebar extends Element {

	public Sidebar(boolean left) {
		if(left){
			x = (Global.width-Global.gamewidth/Global.gameheight*height)/8;
		}else{
			x = Global.width-(Global.width-Global.gamewidth/Global.gameheight*height)/8;
		}
		y = Global.height/2;
		width = (Global.width-Global.gamewidth/Global.gameheight*height)/8;
		height = Global.height;
	}

	@Override
	public void update() {
		
	}
	public void render(){
		glPushMatrix();
		glTranslatef(x,y,0);
		glColor3f(0.05f, 0.05f, 0.05f);
		Graphics.quad(width, height);
		glPopMatrix();
	}
}
