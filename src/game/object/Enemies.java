package game.object;

import events.EventHandler;
import game.mode.GameMode;

public class Enemies {
	public static class BasicEnemy extends Enemy{
		{
			hp = 20;
			size = 12;
		}
		public BasicEnemy(float px, float py) {
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
	public static class ShooterEnemy extends Enemy{
		{
			hp = 20;
			size = 12;
		}
		public ShooterEnemy(float px, float py) {
			super(px, py);
		}
		public void spawn(){
			super.spawn();
			EventHandler.add(new EnemyShooterBulletEvent(1000));
		}
		@Override
		public void update() {
			super.update();
		}
		public void render(){
			super.render();
		}
		class EnemyShooterBulletEvent extends EnemyBulletEvent {

			public EnemyShooterBulletEvent(long time) {
				super(time);
			}
			@Override
			public void run() {
				if(hp<=0)return;
				addBullet(new EnemyBullet(px, py));
				EventHandler.add(new EnemyShooterBulletEvent(1000));
			}
		}
	}
	public static class SniperEnemy extends Enemy{
		{
			hp = 20;
			size = 12;
			speed = 0.1f;
		}
		public SniperEnemy(float px, float py) {
			super(px, py);
		}
		public void spawn(){
			super.spawn();
			EventHandler.add(new EnemyShooterBulletEvent(1000));
		}
		@Override
		public void update() {
			super.update();
		}
		public void render(){
			super.render();
		}
		class EnemyShooterBulletEvent extends EnemyBulletEvent {

			public EnemyShooterBulletEvent(long time) {
				super(time);
			}
			@Override
			public void run() {
				if(hp<=0)return;
				if(GameMode.player!=null)addBullet(new EnemyBullet(px, py, GameMode.player.px-px, GameMode.player.py-py));
				EventHandler.add(new EnemyShooterBulletEvent(1000));
			}
		}
	}
	public static class BossEnemy extends Enemy{
		{
			hp = 500;
			size = 24;
			speed = 0.1f;
		}
		public BossEnemy(float px, float py) {
			super(px, py);
		}
		public void spawn(){
			super.spawn();
			EventHandler.add(new EnemyBossBulletEvent1(4000));
		}
		@Override
		public void update() {
			super.update();
		}
		public void render(){
			super.render();
		}
		private static final float maxcount1 = 10;
		private static float count1 = maxcount1;
		private static final float maxcount2 = 20;
		private static float count2 = maxcount2;
		class EnemyBossBulletEvent1 extends EnemyBulletEvent {
			static final int rings = 24;
			public EnemyBossBulletEvent1(long time) {
				super(time);
			}
			@Override
			public void run() {
				if(hp<=0)return;
				for(float i=0;i<360f;i+=360f/rings){
					addBullet(new EnemyBullet(px, py, 
							(float)Math.sin(Math.toRadians(i+ count1*360f/rings/maxcount1)),
							(float)Math.cos(Math.toRadians(i+ count1*360f/rings/maxcount1))));
				}
				if(count1>0){
					EventHandler.add(new EnemyBossBulletEvent1(400));
					count1-=1;
				}else{
					count1 = maxcount1;
					EventHandler.add(new EnemyBossBulletEvent2(4000));
				}
			}
		}
		class EnemyBossBulletEvent2 extends EnemyBulletEvent {
			static final int rings = 24;
			public EnemyBossBulletEvent2(long time) {
				super(time);
			}
			@Override
			public void run() {
				if(hp<=0)return;
					float dx = (float)Math.sin(Math.toRadians(360f*count2/maxcount2));
					float dy = (float)Math.cos(Math.toRadians(360f*count2/maxcount2));
					addBullet(new EnemyBullet(px, py,dx,dy));
					addBullet(new EnemyBullet(px, py,-dx,-dy));
					
				if(count2>0){
					EventHandler.add(new EnemyBossBulletEvent2(50));
					count2-=0.5f;
				}else{
					count2 = maxcount2;
					EventHandler.add(new EnemyBossBulletEvent1(4000));
				}
			}
		}
	}
	
	
}
