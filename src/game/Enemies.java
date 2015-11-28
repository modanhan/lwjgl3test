package game;

import events.EventHandler;

public class Enemies {
	public static class BasicEnemy extends Enemy{
		boolean hit =false;
		{
			hp = 20;
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
	public static class ShooterEnemy extends Enemy{
		boolean hit =false;
		{
			hp = 20;
			size = 12;
		}
		public ShooterEnemy(int px, int py) {
			super(px, py);
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
		boolean hit =false;
		{
			hp = 20;
			size = 12;
		}
		public SniperEnemy(int px, int py) {
			super(px, py);
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
				addBullet(new EnemyBullet(px, py, GameMode.player.px-px, GameMode.player.py-py));
				EventHandler.add(new EnemyShooterBulletEvent(1000));
			}
		}
	}
}
