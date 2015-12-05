package game;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;

import events.Event;
import events.EventHandler;
import graphics.Graphics;
import util.Keyboard;
import util.Time;

public class Player extends GameEntity {
	private final float SPEED = .3f;
	private final float SLOWSPEED = .1f;
	public int mode = 1;
	public int power=1;
	public Player() {
		size = 10;
		px = GlobalVars.WIDTH / 2;
		py = GlobalVars.HEIGHT / 2;
		shoot();
	}
	public void shoot(){
		EventHandler.add(new PlayerBulletEvent((GlobalVars.cheats&&GlobalVars.bulletstorm)?10:100));
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
		if(GlobalVars.cheats){
			if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_EQUAL)){
				power++;
			}
			if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_MINUS)){
				power--;
			}
		}
		if(px<0)px=0;
		if(py<0)py=0;
		if(px>GlobalVars.WIDTH)px = GlobalVars.WIDTH;
		if(py>GlobalVars.HEIGHT)py = GlobalVars.HEIGHT;
	}

	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(px,py,0);
		glColor3f(1, 1, 1);
		Graphics.quad(size);
		if(power>=8){
			glPushMatrix();
			glTranslatef(size*3f,0,0);
			Graphics.quad(size/2);
			glPopMatrix();
			glPushMatrix();
			glTranslatef(-size*3f,0,0);
			Graphics.quad(size/2);
			glPopMatrix();
		}
		glPopMatrix();
	}
	public void kill(){
		Game.remove(this);
		GameMode.player = null;
	}
	static float seekercount = 0;

	class PlayerBulletEvent extends Event {
		public PlayerBulletEvent(long time) {
			super(time);
		}

		@Override
		public void run() {
			if(GameMode.player==null)return;
			switch(mode){
			case 0:
				normalBullet(power);
				break;
			case 1:
				seekerBullet(power);
				break;
			}
		}
		public void normalBullet(int level){
			shoot();
			switch(level){
			case 1:
				addBullet(new PlayerBullet(px,py));
				break;
			case 2:
				addBullet(new PlayerBullet(px,py,0,-size*0.5f));
				addBullet(new PlayerBullet(px,py,0,+size*0.5f));
				break;
			case 3:
				addBullet(new PlayerBullet(px,py,0,-size));
				addBullet(new PlayerBullet(px,py,0,0));
				addBullet(new PlayerBullet(px,py,0,+size));
				break;
			case 4:
				addBullet(new PlayerBullet(px,py,0,-size*0.5f));
				addBullet(new PlayerBullet(px,py,0,+size*0.5f));
				addBullet(new PlayerBullet(px,py,-20));
				addBullet(new PlayerBullet(px,py,+20));
				break;
			case 5:
				addBullet(new PlayerBullet(px,py,0,-size));
				addBullet(new PlayerBullet(px,py,0,0));
				addBullet(new PlayerBullet(px,py,0,+size));
				addBullet(new PlayerBullet(px,py,-20));
				addBullet(new PlayerBullet(px,py,+20));
				break;
			case 6:
				addBullet(new PlayerBullet(px,py,0,-size*0.5f));
				addBullet(new PlayerBullet(px,py,0,+size*0.5f));
				addBullet(new PlayerBullet(px,py,-20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,-20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,+size*0.5f));
				break;
			case 7:
				addBullet(new PlayerBullet(px,py,0,-size));
				addBullet(new PlayerBullet(px,py,0,0));
				addBullet(new PlayerBullet(px,py,0,+size));
				addBullet(new PlayerBullet(px,py,-20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,-20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,+size*0.5f));
				break;
			case 8:
				addBullet(new PlayerBullet(px,py,0,-size));
				addBullet(new PlayerBullet(px,py,0,0));
				addBullet(new PlayerBullet(px,py,0,+size));
				addBullet(new PlayerBullet(px,py,-20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,-20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,0,-size*3f));
				addBullet(new PlayerBullet(px,py,0,+size*3f));
				break;
			case 9:
				addBullet(new PlayerBullet(px,py,0,-size));
				addBullet(new PlayerBullet(px,py,0,0));
				addBullet(new PlayerBullet(px,py,0,+size));
				addBullet(new PlayerBullet(px,py,-20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,-20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,0,-size*2.5f));
				addBullet(new PlayerBullet(px,py,0,+size*2.5f));
				addBullet(new PlayerBullet(px,py,0,-size*3.5f));
				addBullet(new PlayerBullet(px,py,0,+size*3.5f));
				break;
			case 10:
				addBullet(new PlayerBullet(px,py,0,-size));
				addBullet(new PlayerBullet(px,py,0,0));
				addBullet(new PlayerBullet(px,py,0,+size));
				addBullet(new PlayerBullet(px,py,-20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,-20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,-size*0.5f));
				addBullet(new PlayerBullet(px,py,+20,+size*0.5f));
				addBullet(new PlayerBullet(px,py,0,-size*2.5f));
				addBullet(new PlayerBullet(px,py,0,+size*2.5f));
				addBullet(new PlayerBullet(px,py,0,-size*3.5f));
				addBullet(new PlayerBullet(px,py,0,+size*3.5f));
				addBullet(new PlayerBullet(px-size*3f,py,-20));
				addBullet(new PlayerBullet(px+size*3f,py,-20));
				addBullet(new PlayerBullet(px-size*3f,py,+20));
				addBullet(new PlayerBullet(px+size*3f,py,+20));
				break;


			}

		}
		public void seekerBullet(int level){
			final float angle = 18;
			EventHandler.add(new PlayerBulletEvent((GlobalVars.cheats&&GlobalVars.bulletstorm)?10:100));
			addBullet(new PlayerBullet(px,py));
			if(seekercount>0){
				seekercount--;
			}else{
				seekercount = 10;
				switch(level){
				case 1:
					break;
				case 2:
					addBullet(new SeekerBullet(px,py));
					break;
				case 3:
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					break;
				case 4:
					addBullet(new SeekerBullet(px,py,-3*angle));
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					addBullet(new SeekerBullet(px,py,+3*angle));
					break;
				case 5:
					seekercount--;
					addBullet(new SeekerBullet(px,py,-3*angle));
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					addBullet(new SeekerBullet(px,py,+3*angle));
					break;
				case 6:
					seekercount--;
					addBullet(new SeekerBullet(px,py,-5*angle));
					addBullet(new SeekerBullet(px,py,-3*angle));
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					addBullet(new SeekerBullet(px,py,+3*angle));
					addBullet(new SeekerBullet(px,py,+5*angle));
					break;
				case 7:
					seekercount--;
					addBullet(new SeekerBullet(px,py,-7*angle));
					addBullet(new SeekerBullet(px,py,-5*angle));
					addBullet(new SeekerBullet(px,py,-3*angle));
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					addBullet(new SeekerBullet(px,py,+3*angle));
					addBullet(new SeekerBullet(px,py,+5*angle));
					addBullet(new SeekerBullet(px,py,+7*angle));
					break;
				case 8:
					seekercount-=3;
					addBullet(new SeekerBullet(px,py,-7*angle));
					addBullet(new SeekerBullet(px,py,-5*angle));
					addBullet(new SeekerBullet(px,py,-3*angle));
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					addBullet(new SeekerBullet(px,py,+3*angle));
					addBullet(new SeekerBullet(px,py,+5*angle));
					addBullet(new SeekerBullet(px,py,+7*angle));
					break;
				case 9:
					seekercount-=3;
					addBullet(new SeekerBullet(px,py,-9*angle));
					addBullet(new SeekerBullet(px,py,-7*angle));
					addBullet(new SeekerBullet(px,py,-5*angle));
					addBullet(new SeekerBullet(px,py,-3*angle));
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					addBullet(new SeekerBullet(px,py,+3*angle));
					addBullet(new SeekerBullet(px,py,+5*angle));
					addBullet(new SeekerBullet(px,py,+7*angle));
					addBullet(new SeekerBullet(px,py,+9*angle));
					break;
				case 10:
					seekercount-=5;
					addBullet(new SeekerBullet(px,py,-9*angle));
					addBullet(new SeekerBullet(px,py,-7*angle));
					addBullet(new SeekerBullet(px,py,-5*angle));
					addBullet(new SeekerBullet(px,py,-3*angle));
					addBullet(new SeekerBullet(px,py,-angle));
					addBullet(new SeekerBullet(px,py,+angle));
					addBullet(new SeekerBullet(px,py,+3*angle));
					addBullet(new SeekerBullet(px,py,+5*angle));
					addBullet(new SeekerBullet(px,py,+7*angle));
					addBullet(new SeekerBullet(px,py,+9*angle));
					break;
				}
				
			}
		}
		void addBullet(PlayerBullet p){
			Game.add(p);
			GameMode.playerbullets.add(p);
		}

	}

	class PlayerBullet extends Bullet {
		public int power=1;
		{
			size=4;
			speed=0.4f;
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
		PlayerBullet(float px, float py, float dir,float offset){
			this.px = px+(float)(Math.cos(Math.toRadians(dir))*offset);
			this.py = py-(float)(Math.sin(Math.toRadians(dir))*offset);
			this.dx=(float) (Math.sin(Math.toRadians(dir))*speed);
			this.dy=(float) (Math.cos(Math.toRadians(dir))*speed);
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
			Graphics.quad(size);
			glPopMatrix();
		}

	}
	class SeekerBullet extends PlayerBullet {
		static final float ACCELERATION = 4.0f;
		static final float MINSPEED = 0.4f;
		static final float MAXSPEED = 0.4f;
		static final float TARGETTIME = 0.8f;
		float t=0;
		{
			size=4;
			speed=0.4f;
			power = 3;
		}
		SeekerBullet(float px, float py) {
			super(px,py);
		}
		SeekerBullet(float px, float py, float dir){
			super(px,py,dir);
		}
		SeekerBullet(float px, float py, float dir,float offset){
			super(px,py,dir,offset);
		}
		@Override
		public void update() {
			super.update();
			float d = Time.getDelta()/1000f;
			t+=d;
			Enemy nearest = null;
			float distance=Float.MAX_VALUE;
			for(Enemy e:GameMode.enemies){
				float cdist = GameEntity.getDistance(this, e);
				if(cdist<distance){
					nearest = e;
					distance = cdist;
				}
			}
			if(nearest!=null&&t<=TARGETTIME){
				float tx = nearest.px;
				float ty = nearest.py;
				dx = dx*(TARGETTIME-t)/TARGETTIME+(tx-px)/distance*t/TARGETTIME*ACCELERATION*d;
				dy = dy*(TARGETTIME-t)/TARGETTIME+(ty-py)/distance*t/TARGETTIME*ACCELERATION*d;
				float speed = (float) Math.hypot(dx, dy);
				if(speed>MAXSPEED){
					dx = dx*MAXSPEED/speed;
					dy = dy*MAXSPEED/speed;
				}
				if(speed<MINSPEED){
					dx = dx*MINSPEED/speed;
					dy = dy*MINSPEED/speed;
				}
			}
		}
		public void kill(){
			super.kill();
		}
		@Override
		public void render() {
			super.render();
		}

	}
}
