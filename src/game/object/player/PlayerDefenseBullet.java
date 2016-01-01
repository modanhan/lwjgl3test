package game.object.player;

import java.util.ListIterator;

import game.Game;
import game.object.Bullet;
import game.object.GameObject;
import game.object.enemy.Enemy;
import util.Global;
import util.Time;

public class PlayerDefenseBullet extends PlayerSeekerBullet {
	public static int count = 0;
	public static int maxcount = 0;
	private boolean targeted = false;
	private float currenttime = 0;
	public PlayerDefenseBullet(float dir) {
		super(dir);
		time=0;
		
	}
	@Override
	public void update() {
		this.track();
		updateLocation();
	}
	public void track() {
		float d = Time.getDelta() / 1000f;
		GameObject target = null;
		float distance = Float.MAX_VALUE;
		ListIterator<Enemy> iter = targets.listIterator();
		while (iter.hasNext()) {
			GameObject g = iter.next();
			float dist = (float) Math.hypot(g.x - x, g.y - y);
			if (dist <= distance) {
				distance = dist;
				target = g;
			}
		}
		if (targeted) {
			if (target != null && time < Global.seeker_bullet_targetting_time) {
				time += d;
				float tx = target.x;
				float ty = target.y;
				dx = dx * (Global.seeker_bullet_targetting_time - time) / Global.seeker_bullet_targetting_time
						+ (tx - x) / distance * time / Global.seeker_bullet_targetting_time
								* Global.seeker_bullet_acceleration * d;
				dy = dy * (Global.seeker_bullet_targetting_time - time) / Global.seeker_bullet_targetting_time
						+ (ty - y) / distance * time / Global.seeker_bullet_targetting_time
								* Global.seeker_bullet_acceleration * d;
				float speed = (float) Math.hypot(dx, dy);
				if (speed > Global.seeker_bullet_maximum_speed) {
					dx = dx * Global.seeker_bullet_maximum_speed / speed;
					dy = dy * Global.seeker_bullet_maximum_speed / speed;
				} else if (speed < Global.seeker_bullet_minimum_speed) {
					dx = dx * Global.seeker_bullet_minimum_speed / speed;
					dy = dy * Global.seeker_bullet_minimum_speed / speed;
				}
			}
		} else {
			currenttime += d;
			targeted = distance < Global.player_defense_bullet_range;
			if(Game.player != null){
			setDir((float) Math.atan2(Game.player.y + Math.cos(currenttime*Global.Dir.PI2) * Global.player_defense_bullet_dist - y ,
					Game.player.x + Math.sin(currenttime*Global.Dir.PI2) * Global.player_defense_bullet_dist - x));
			}
		}
	}
	public PlayerDefenseBullet clone(){
		if(count>=maxcount){
			return null;
		}
		count++;
		return new PlayerDefenseBullet(getDir());
	}
	public Bullet clone(float x, float y) {
		Bullet b = clone();
		if(b!=null){
			b.x = x;
			b.y = y;
		}
		return b;
	}
	public void death(){
		super.death();
		count--;
	}
}
