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
		public int time = 0;
		public static Bullet b = new EnemyLinearBullet(0.2f,Global.Dir.DOWN);;
		public BulletEnemy(float x, float y) {
			super(x, y);
		}
		public void update(){
			super.update();
			time += Time.getDelta();
			if(time > 1000){
				Game.addEnemyBullet(b.clone(x,y));
				time -= 1000;
			}
		}
	}
}
