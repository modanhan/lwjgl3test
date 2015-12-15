package game.object;

import static org.lwjgl.opengl.GL11.*;

import java.util.ListIterator;

import events.Event;
import game.Game;
import game.object.Player.PlayerBullet;
import graphics.Graphics;
import util.Global;
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
		Game.addEnemy(this);
	}
	public void death(){
		super.death();
		hp=0;
	}
	@Override
	public void render() {
		glPushMatrix();
		glTranslatef(px, py, 0);
		if(!hit){
			glColor3f(0, 0, 1);
		}else{
			glColor3f(1, 1, 1);
		}
		Graphics.quad(size);
		glPopMatrix();
	}
	public void renderGlow(){
		render();
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
		ListIterator<GameObject> i = Game.playerbullets.listIterator();
		while (i.hasNext()){
			b = (PlayerBullet) i.next();
			if(CircleGameObject.checkCollision(this, b)){
				b.remove();
				i.remove();
				hp-=b.power;
				hit=true;
			}
		}
		if(Game.player!=null){
			if(CircleGameObject.checkCollision(this, Game.player)){
				if(!(Global.godmode&&Global.cheats)){
					Game.player.kill();
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
		public void addBullet(EnemyBullet p){
			Game.enemybullets.add(p);
		}
	}

	public class EnemyBullet extends Bullet {
		{
			size=4;
			speed=0.2f;
		}
		public EnemyBullet(float px, float py) {
			this.px = px;
			this.py = py;
			this.dx=0;
			this.dy=-speed;
		}
		public EnemyBullet(float px, float py, float dir){
			this.px = px;
			this.py = py;
			this.dx=(float) (Math.sin(Math.toRadians(dir))*speed);
			this.dy=(float) (Math.cos(Math.toRadians(dir))*speed);
		}
		public EnemyBullet(float px, float py, float dx, float dy) {
			this.px = px;
			this.py = py;
			this.dx=(float) (dx/Math.hypot(dx, dy)*speed);
			this.dy=(float) (dy/Math.hypot(dx, dy)*speed);
		}
		@Override
		public void update() {
			super.update();
			if(Game.player!=null){
				if(checkCollision(this,Game.player)){
					if(!(Global.godmode&&Global.cheats)){
						Game.player.kill();
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
			glColor3f(0, 0, 1);
			Graphics.quad(size);
			glPopMatrix();
		}
		public void renderGlow(){
			glPushMatrix();
			glTranslatef(px, py, 0);
			glRotatef((float) Math.toDegrees(Math.atan2(dy, dx)), 0, 0, 1);
			glColor3f(0.5f, 0.5f, 1);
			Graphics.quad(size);
			glPopMatrix();
		}
	}
	public static class EnemySpawnEvent extends Event{
		Enemy e;
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
