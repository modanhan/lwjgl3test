package game.object.player;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;
import game.Attack;
import game.Game;
import game.object.CollidingGameObject;
import game.object.visual.ExplosionVisual;
import graphics.Graphics;
import graphics.Texture;
import util.Global;
import util.Keyboard;
import util.Time;

public class Player extends CollidingGameObject {

	Attack attack;
	int powertype = 0, powerlevel = 0;
	ArrayList<SideShooter> sideshooters;

	public Player() {
		super(CIRCLE);
		radius = Global.player_size;
		x = Global.gamewidth / 2;
		y = Global.gameheight / 2;
		sideshooters = new ArrayList<SideShooter>();
	}

	public void receivePowerup(int type) {
		if(type == powertype){
			powerlevel += 1;
			if(powerlevel > 14) powerlevel = 14;
		}
		switchAttack(type, powerlevel);
		powertype = type;
	}

	public void switchAttack(int type, int level) {
		attack = PlayerAttacks.acquire(type, level);
		attack.activate();
		SideShooterAttacks.acquire(type, level);
	}

	/**
	 * Switches to a linear attack with specified powerlevel
	 * 
	 * @param powerlevel
	 */

	@Override
	public void update() {
		float speed = Global.player_speed;
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
			speed = Global.player_slow_speed;
		}
		int d = Time.getDelta();
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
			y += (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			y -= (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			x -= (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			x += (d * speed);
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_EQUAL)) {
			powerlevel = (powerlevel + 1) % 15;
			switchAttack(powertype, powerlevel);
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_MINUS)) {
			powerlevel = (powerlevel + 14) % 15;
			switchAttack(powertype, powerlevel);
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_RIGHT_BRACKET)) {
			powertype = (powertype + 1) % 3;
			switchAttack(powertype, powerlevel);
		}
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_LEFT_BRACKET)) {
			powertype = (powertype + 2) % 3;
			switchAttack(powertype, powerlevel);
		}

		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x > Global.gamewidth)
			x = Global.gamewidth;
		if (y > Global.gameheight)
			y = Global.gameheight;

		for (SideShooter s : sideshooters) {
			s.update();
		}

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
		for (SideShooter s : sideshooters) {
			s.render();
		}
	}

	public void renderGlow() {
		Texture.bind(Global.Textures.circle);
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor4f(1, 1, 1, .95f);
		Graphics.quad(radius * 2f);
		glPopMatrix();
	}
	
	public void death() {
		Game.addVisuals(new ExplosionVisual(this.x, this.y, 0, 720, 2500));
	}

	@Override
	public Player clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
