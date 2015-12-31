package game.object.player;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import util.Global;
import util.Time;
import game.Attack;
import game.Game;
import game.object.GameObject;
import graphics.Graphics;
import graphics.Texture;

public class SideShooter extends GameObject {

	float radius, dist, dir, speed;
	private Attack attack;

	public SideShooter(float radius, float dist, float dir, float speed) {
		this(radius, dist, dir, speed, null);
	}

	public SideShooter(float radius, float dist, float dir, float speed,
			Attack attack) {
		this.radius = radius;
		this.dist = dist;
		this.dir = dir;
		this.speed = speed;
		x = (float) (Game.player.x + Math.cos(dir) * dist);
		y = (float) (Game.player.y + Math.sin(dir) * dist);
		this.attack = attack;
	}
	
	public void setAttack(Attack attack){
		this.attack=attack;
	}

	@Override
	public void update() {
		dir += speed * Time.getDelta();
		x = (float) (Game.player.x + Math.cos(dir) * dist);
		y = (float) (Game.player.y + Math.sin(dir) * dist);
		if (attack != null)
			attack.update();
	}

	@Override
	public void render() {
		Texture.bind(Global.Textures.circle);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor3f(1, 1, 1);
		Graphics.quad(radius);
		glPopMatrix();
	}

	@Override
	public void death() {
		// TODO Auto-generated method stub

	}
	
	public void activateAttack(){
		attack.activate();
	}

}
