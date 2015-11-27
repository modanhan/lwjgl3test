package game;

import java.util.List;
import java.util.ListIterator;

import org.lwjgl.opengl.GL11;

import events.Event;
import events.EventHandler;
import game.Player.PlayerBullet;
import util.Time;

public abstract class Enemy extends GameEntity{
	protected boolean hit = false;
	protected int hp=1;
	public Enemy(int px,int py) {
		this.px = px;
		this.py = py;
	}
	@Override
	public void render() {
		GL11.glPushMatrix();
		GL11.glTranslatef(px, py, 0);
		if(!hit){
			GL11.glColor3f(1, 0, 0);
		}else{
			GL11.glColor3f(1, 1, 1);
		}
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(- size, - size);
		GL11.glVertex2f(- size, + size);
		GL11.glVertex2f(+ size, + size);
		GL11.glVertex2f(+ size, - size);
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	public void update() {
		hit = false;
		Bullet b = null;
		ListIterator<PlayerBullet> i = GameMode.playerbullets.listIterator();
		while (i.hasNext()){
			b = i.next();
			if(GameEntity.checkCollision(this, b)){
				Game.remove(b);
				i.remove();
				hp--;
				hit=true;
			}
		}
		if(hp<=0){
			kill();
		}
	}
	class EnemyBulletEvent extends Event {

		public EnemyBulletEvent(long time) {
			super(time);
		}

		@Override
		public void run() {
			Game.add(new EnemyBullet(px, py));
		}

	}

	class EnemyBullet extends Bullet {
		{
			size=4;
			speed=0.1f;
		}
		EnemyBullet(float px, float py) {
			this.px = px;
			this.py = py;
			this.dx=0;
			this.dy=-speed;
		}
		EnemyBullet(float px, float py, float dir){
			this.px = px;
			this.py = py;
			this.dx=(float) (Math.sin(Math.toRadians(dir))*speed);
			this.dy=(float) (Math.cos(Math.toRadians(dir))*speed);
		}
		EnemyBullet(float px, float py, float dx, float dy) {
			this.px = px;
			this.py = py;
			this.dx=(float) (dx/Math.hypot(dx, dy)*speed);
			this.dy=(float) (dy/Math.hypot(dx, dy)*speed);
		}
		@Override
		public void update() {
			super.update();
			if(checkCollision(this,GameMode.player)){
				GameMode.player.kill();
				
			}
		}

		@Override
		public void render() {
			GL11.glPushMatrix();
			GL11.glTranslatef(px, py, 0);
			GL11.glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			GL11.glColor3f(1, 1, 1);
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glVertex2f( - size,  - size);
			GL11.glVertex2f( - size,  + size);
			GL11.glVertex2f( + size,  + size);
			GL11.glVertex2f( + size,  - size);
			GL11.glEnd();
			GL11.glPopMatrix();
		}

	}
}
