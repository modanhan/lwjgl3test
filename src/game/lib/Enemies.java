package game.lib;

import game.object.enemy.Enemy;
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
}
