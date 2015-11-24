package game;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import util.Keyboard;
import util.Time;

public class Player extends GameObject {
	private final int L = 10;
	private final float SPEED = .3f;
	private int px, py;

	public Player() {
		px = GlobalVars.WIDTH / 2;
		py = GlobalVars.HEIGHT / 2;
	}

	@Override
	public void update() {
		if (Keyboard.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			System.out.println("updating");
		}
		int d = Time.getDelta();
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
			py += (int) (d * SPEED);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			py -= (int) (d * SPEED);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			px -= (int) (d * SPEED);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			px += (int) (d * SPEED);
		}
	}

	@Override
	public void render() {
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(px - L, py - L);
		GL11.glVertex2f(px - L, py + L);
		GL11.glVertex2f(px + L, py + L);
		GL11.glVertex2f(px + L, py - L);
		GL11.glEnd();
	}

}
