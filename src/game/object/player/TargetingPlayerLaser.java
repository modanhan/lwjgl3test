package game.object.player;

import game.Game;
import game.object.Bullet;
import game.object.enemy.Enemy;
import util.Global;

public class TargetingPlayerLaser extends PlayerLaser {

	public TargetingPlayerLaser(float x, float y) {
		super(Global.Dir.UP);
		float ans = Global.Dir.UP, mind = Float.MAX_VALUE;
		for (Enemy e : Game.enemies) {
			float d = (float) Math.hypot(e.x - x, e.y - y);
			if (d <= mind) {
				mind = d;
				ans = (float) Math.atan2(e.y - y, e.x - x);
			}
		}
		this.x = x;
		this.y = y;
		theta = ans;
	}

	@Override
	public Bullet clone() {
		return clone(x, y);
	}

	@Override
	public Bullet clone(float x, float y) {
		return new TargetingPlayerLaser(x, y);
	}

}
