package game.object;

import static org.lwjgl.opengl.GL11.*;

import java.util.ListIterator;

import events.Event;
import game.Game;
import game.mode.GameMode;
import game.object.Player.PlayerBullet;
import global.Global;
import graphics.Graphics;
import util.Time;

public abstract class Enemy extends CircleGameObject{
	protected boolean hit = false;
	public int hp=1;
	protected float tx,ty,speed=0.1f;
	public Enemy(float px,float py) {
		this.px = px;
		this.py = py;
		this.tx = px;
		this.ty = py;
	}
	public void spawn(){
		Game.add(this);
		GameMode.enemies.add(this);
	}
	public void death(){
		super.death();
		GameMode.enemies.remove(this);
		hp=0;
	}
	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(px, py, 0);
		if(!hit){
			glColor3f(1, 0, 0);
		}else{
			glColor3f(1, 1, 1);
		}
		Graphics.quad(size);
		glPopMatrix();
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
		PlayerBullet b = null;
		ListIterator<PlayerBullet> i = GameMode.playerbullets.listIterator();
		while (i.hasNext()){
			b = i.next();
			if(CircleGameObject.checkCollision(this, b)){
				b.remove();
				i.remove();
				hp-=b.power;
				hit=true;
			}
		}
		if(GameMode.player!=null){
			if(CircleGameObject.checkCollision(this, GameMode.player)){
				if(!(Global.godmode&&Global.cheats)){
					GameMode.player.kill();
				}
				hp--;
				hit=true;
			}
		}
		if(hp<=0){
			kill();
		}
	}

	public class EnemyBulletEvent extends Event {

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

	public class EnemyBullet extends Bullet {
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
					if(!(Global.godmode&&Global.cheats)){
						GameMode.player.kill();
					}
					this.kill();
				}
			}
		}

		@Override
		public void render() {
			glPushMatrix();
			glTranslatef(px, py, 0);
			glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			glColor3f(1, 0, 0);
			Graphics.quad(size);
			glPopMatrix();
		}
	}
	public static class EnemySpawnEvent extends Event{
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
	public static class EnemyDespawnEvent extends Event{
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
	public static class EnemyMoveEvent extends Event{
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
