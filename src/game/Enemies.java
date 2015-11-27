package game;

import java.util.ListIterator;

import org.lwjgl.opengl.GL11;

import game.Player.PlayerBullet;

public class Enemies {
	public static class BasicEnemy extends Enemy{
		boolean hit =false;
		{
			hp = 40;
			size = 12;
		}
		public BasicEnemy(int px, int py) {
			super(px, py);
		}

		@Override
		public void update() {
			super.update();
		}
		public void render(){
			super.render();
		}

	}
}
