package game.object.player;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;
import game.Attack;
import game.Game;
import game.object.CollidingGameObject;
import game.object.visual.ExplosionVisual;
import graphics.Graphics;
import util.Global;
import util.Keyboard;
import util.Time;

public class Player extends CollidingGameObject {

	Attack attack;

	public Player() {
		super(CIRCLE);
		radius = Global.player_size;
		x = Global.width / 2;
		y = Global.height / 2;
		attack = new Attack(this, true) {

			@Override
			public void init() {
				addBullet(new PlayerLinearBullet(), Global.player_bullet_delay);
			}
		};
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

		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x > Global.width)
			x = Global.width;
		if (y > Global.height)
			y = Global.height;

		attack.update();
	}

	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(x, y, 0);
		glColor3f(1, 1, 1);
		Graphics.quad(radius);
		glPopMatrix();
	}

	public void renderGlow() {
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
