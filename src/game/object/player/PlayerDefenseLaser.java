package game.object.player;

import game.Game;
import game.object.Bullet;
import util.Global;

public class PlayerDefenseLaser extends PlayerLaser{
	public PlayerDefenseLaser() {
		this(Game.player.x, Game.player.y);
	}
	public PlayerDefenseLaser(float x, float y) {
		super(Global.Dir.UP);
		float ans = Global.Dir.UP, mind = Global.player_defense_laser_range;
		Bullet target = null;
		for (Bullet e : Game.enemybullets) {
			float d = (float) Math.hypot(e.x - x, e.y - y);
			if (d <= mind) {
				mind = d;
				ans = (float) Math.atan2(e.y - y, e.x - x);
				target = e;
			}
		}
		if(target!=null){
			target.kill();
			radius = 2;
		}else{
			radius = 0;
		}
		this.x = x;
		this.y = y;
		theta = ans;
		damage = 0;
		
	}

	@Override
	public Bullet clone() {
		return clone(x, y);
	}

	@Override
	public Bullet clone(float x, float y) {
		return new PlayerDefenseLaser(x, y);
	}
}



