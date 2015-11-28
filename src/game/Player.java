package game;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;

import events.Event;
import events.EventHandler;
import util.Keyboard;
import util.Time;

public class Player extends GameEntity {
	private final float SPEED = .3f;
	private final float SLOWSPEED = .1f;
	public Player() {
		size = 10;
		px = GlobalVars.WIDTH / 2;
		py = GlobalVars.HEIGHT / 2;
		EventHandler.add(new PlayerBulletEvent(200));
	}

	@Override
	public void update() {
		float speed = SPEED;
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
			speed = SLOWSPEED;
		}
		int d = Time.getDelta();
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
			py += (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			py -= (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			px -= (d * speed);
		}
		if (Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			px += (d * speed);
		}
	}

	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(px,py,0);
		glColor3f(1, 1, 1);
		glBegin(GL_TRIANGLE_FAN);
		glTexCoord2f(0,0); glVertex2f(- size, - size);
		glTexCoord2f(0,1); glVertex2f(- size, + size);
		glTexCoord2f(1,1); glVertex2f(+ size, + size);
		glTexCoord2f(1,0); glVertex2f(+ size, - size);
		glEnd();
		glPopMatrix();
	}
	public void kill(){
		EventHandler.clear();
		Game.remove(this);
		GameMode.player = null;
	}
	class PlayerBulletEvent extends Event {

		public PlayerBulletEvent(long time) {
			super(time);
		}

		@Override
		public void run() {
			EventHandler.add(new PlayerBulletEvent((GlobalVars.cheats&&GlobalVars.bulletstorm)?10:100));

			addBullet(new PlayerBullet(px, py, -30));
			addBullet(new PlayerBullet(px, py, -15));
			addBullet(new PlayerBullet(px, py));
			addBullet(new PlayerBullet(px, py, 15));
			addBullet(new PlayerBullet(px, py, 30));

		}
		void addBullet(PlayerBullet p){
			Game.add(p);
			GameMode.playerbullets.add(p);
		}
	}

	class PlayerBullet extends Bullet {
		{
			size=4;
			speed=0.3f;
		}
		PlayerBullet(float px, float py) {
			this.px = px;
			this.py = py;
			this.dx=0;
			this.dy=speed;
		}
		PlayerBullet(float px, float py, float dir){
			this.px = px;
			this.py = py;
			this.dx=(float) (Math.sin(Math.toRadians(dir))*speed);
			this.dy=(float) (Math.cos(Math.toRadians(dir))*speed);
		}
		PlayerBullet(float px, float py, float dx, float dy) {
			this.px = px;
			this.py = py;
			this.dx=(float) (dx/Math.hypot(dx, dy)*speed);
			this.dy=(float) (dy/Math.hypot(dx, dy)*speed);
		}
		@Override
		public void update() {
			super.update();
		}
		public void kill(){
			Game.remove(this);
			GameMode.playerbullets.remove(this);
		}
		@Override
		public void render() {
			glPushMatrix();
			glTranslatef(px, py, 0);
			glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			glColor3f(1, 1, 1);
			glBegin(GL_TRIANGLE_FAN);
			glTexCoord2f(0,0); glVertex2f( - size,  - size);
			glTexCoord2f(0,1); glVertex2f( - size,  + size);
			glTexCoord2f(1,1); glVertex2f( + size,  + size);
			glTexCoord2f(1,0); glVertex2f( + size,  - size);
			glEnd();
			glPopMatrix();
		}

	}

}
