package game;

import java.util.ListIterator;

import org.lwjgl.opengl.GL11;

import events.Event;
import game.Player.PlayerBullet;
import util.Time;

public abstract class Enemy extends GameEntity{
	protected boolean hit = false;
	protected int hp=1;
	protected float tx,ty,speed=0.1f;
	public Enemy(float px,float py) {
		this.px = px;
		this.py = py;
		this.tx = px;
		this.ty = py;
	}
	public void spawn(){
		Game.add(this);
	}
	public void kill(){
		super.kill();
		hp=0;
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
	protected void move(){
		float d = Time.getDelta();
		float dist = (float) Math.hypot(ty-py, tx-px);
		if(dist>speed*d){
			px += (tx-px)/dist*speed*d;
			py += (ty-py)/dist*speed*d;
		}else{
			px=tx;
			py=ty;
		}
	}
	public void update() {
		move();
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
		if(GameMode.player!=null){
			if(GameEntity.checkCollision(this, GameMode.player)){
				GameMode.player.kill();
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
			if(hp<=0)return;
			addBullet(new EnemyBullet(px, py));
		}
		void addBullet(EnemyBullet p){
			Game.add(p);
			GameMode.enemybullets.add(p);
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
			if(GameMode.player!=null){
				if(checkCollision(this,GameMode.player)){
					GameMode.player.kill();
					this.kill();
				}
			}
		}

		@Override
		public void render() {
			GL11.glPushMatrix();
			GL11.glTranslatef(px, py, 0);
			GL11.glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			GL11.glColor3f(1, 0, 0);
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glVertex2f( - size,  - size);
			GL11.glVertex2f( - size,  + size);
			GL11.glVertex2f( + size,  + size);
			GL11.glVertex2f( + size,  - size);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}
	static class EnemySpawnEvent extends Event{
		Enemy e;
		public EnemySpawnEvent(long time) {
			super(time);
		}
		public EnemySpawnEvent(long time,Enemy e) {
			super(time);
			this.e = e;
		}

		@Override
		public void run() {
			e.spawn();
		}
		
	}
	static class EnemyDespawnEvent extends Event{
		Enemy e;
		public EnemyDespawnEvent(long time) {
			super(time);
		}
		public EnemyDespawnEvent(long time,Enemy e) {
			super(time);
			this.e = e;
		}

		@Override
		public void run() {
			e.kill();
		}
		
	}
	static class EnemyMoveEvent extends Event{
		Enemy e;
		float x,y;
		public EnemyMoveEvent(long time) {
			super(time);
		}
		public EnemyMoveEvent(long time,Enemy e,float x,float y) {
			super(time);
			this.e = e;
			this.x = x;
			this.y = y;
		}
		@Override
		public void run() {
			e.tx=x;
			e.ty=y;
		}
		
	}
	
}
