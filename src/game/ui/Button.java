package game.ui;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import graphics.Graphics;
import util.Mouse;

public abstract class Button extends Element {
	boolean hovered = false;
	public Button(int x,int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	@Override
	public void update() {
		hovered = false;
		if(Mouse.getX()<x+width&&Mouse.getX()>x-width){
			if(Mouse.getY()<y+height&&Mouse.getY()>y-height){
				hovered = true;
				if(Mouse.isButtonPressed(0)){
					onClick();
				}
			}
		}
	}
	public void render(){
		if(hovered){
			renderHovered();
		}else{
			glPushMatrix();
			glTranslatef(x,y,0);
			glColor3f(.5f, .5f, .5f);
			Graphics.quad(width, height);
			glPopMatrix();
		}
	}
	public void renderHovered(){
		glPushMatrix();
		glTranslatef(x,y,0);
		glColor3f(1, 1, 1);
		Graphics.quad(width, height);
		glPopMatrix();
	}
	public abstract void onClick();
}
