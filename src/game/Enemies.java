package game;

import java.util.ListIterator;

import org.lwjgl.opengl.GL11;

import events.Event;
import events.EventHandler;
import game.Enemy.EnemyBullet;
import game.Player.PlayerBullet;

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
				addBullet(new EnemyBullet(px, py));
				if(!(hp<=0))EventHandler.add(new EnemyShooterBulletEvent(1000));
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
				addBullet(new EnemyBullet(px, py, GameMode.player.px-px, GameMode.player.py-py));
				if(!(hp<=0))EventHandler.add(new EnemyShooterBulletEvent(1000));
			}
		}
	}
}
