package game.lib;

import game.Game;
import game.object.Bullet;
import game.object.enemy.Enemy;
import game.object.enemy.EnemyLinearBullet;
import util.Global;
import util.Time;

public class Enemies {
	public static class DPSCalcEnemy extends Enemy{
		public int prevhp = hp;
		public float time =0;
		public final float calctime = 5;
		public DPSCalcEnemy(float x, float y) {
			super(x, y);
		}
		public void update(){
			super.update();
			time += Time.getDelta()/1000f;
			if(time>=calctime){
				System.out.println("DPS: "+(prevhp-hp)/calctime);
				prevhp = hp;
				time -= calctime;
			}
		}
	}
	public static class WeakEnemy extends Enemy{

		public WeakEnemy(float x, float y) {
			super(x, y);
			hp = 1;
			tx = x;
			ty = - Global.margin - 1;
			radius = 4;
		}
		@Override
		public void update() {
			super.update();
			if(y < - Global.margin){
				kill();
			}
		}
	}
	public static class SwarmEnemy extends Enemy{

		public SwarmEnemy(float x, float y) {
			super(x, y);
			hp = 1;
			if(Game.player!=null){
				tx = Game.player.x;
				ty = Game.player.y;
			}
			radius = 4;
		}
		@Override
		public void update() {
			super.update();
			if(Game.player!=null){
				tx = Game.player.x;
				ty = Game.player.y;
			}

		}
	}
	public static class BulletEnemy extends Enemy{
		public int time = 0,delay=1000;
		public static Bullet b = new EnemyLinearBullet(0.2f,Global.Dir.DOWN);
		public BulletEnemy(float x, float y) {
			super(x, y);
		}
		public void update(){
			super.update();
			time += Time.getDelta();
			if(time > delay){
				Game.addEnemyBullet(b.clone(x,y));
				time -= delay;
			}
		}
	}
	
	public static class SniperEnemy extends Enemy{
		public int delay=1000,time=0;
		
		public SniperEnemy(float x, float y) {
			super(x, y);
		}

		static Bullet b = new EnemyLinearBullet(0.2f, Global.Dir.DOWN);

		public void update() {
			time += Time.getDelta();
			if (time > delay) {
				if (Game.player != null) {
					float dir = (float) Math.atan2(Game.player.y - this.y, Game.player.x - this.x);
					Bullet actualb = b.clone(x, y);
					actualb.setDir(dir);
					Game.addEnemyBullet(actualb);
					time -= delay;
				}
			}
			super.update();
		}
	}
	
	public static class SniperSplashEnemy extends Enemy{
		public int delay=2500,time=0;
		int n;
		float coneangle=0;
		public SniperSplashEnemy(float x, float y,int n,float coneangle) {
			super(x, y);
			radius=Global.sniper_splash_enemy_radius;
			this.coneangle=coneangle;
			this.n=n;
		}

		static Bullet b = new EnemyLinearBullet(0.2f, Global.Dir.DOWN);

		public void update() {
			time += Time.getDelta();
			if (time > delay) {
				if (Game.player != null) {
					float dir = (float) Math.atan2(Game.player.y - this.y, Game.player.x - this.x);
					Bullet actualb = b.clone(x, y);
					actualb.setDir(dir);
					Game.addEnemyBullet(actualb);
					for(int i=0;i<n;i++){
						Bullet b1 = b.clone(x, y);b1.setDir(dir-coneangle*(i+1));
						Bullet b2 = b.clone(x, y);b2.setDir(dir+coneangle*(i+1));
						Game.addEnemyBullet(b1);
						Game.addEnemyBullet(b2);
					}
					time -= delay;
				}
			}
			super.update();
		}
	}
}
