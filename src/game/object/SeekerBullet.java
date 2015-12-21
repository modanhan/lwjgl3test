package game.object;

import java.util.List;
import java.util.ListIterator;

import util.Global;
import util.Time;

public abstract class SeekerBullet extends LinearBullet {
	public List<GameObject> targets;
	
	private float time;
	/**
	 * 
	 * @param px
	 *            the starting position x
	 * @param py
	 *            the starting position y
	 * @param dir
	 *            the direction in radians, e.g. 0 = right, Math.PI/2 = up
	 * @param speed
	 *            the speed the bullet travels
	 * @return
	 */
	public SeekerBullet(float px, float py, float dir, float speed,List<GameObject> targets) {
		super(px, py, dir, speed);
		this.targets=targets;
		time =0;
	}
	public void track(){
		float d = Time.getDelta()/1000f;
		time+=d;
		CircleGameObject target = null;
		float distance = Float.MAX_VALUE;
		ListIterator<GameObject> iter = targets.listIterator();
		while(iter.hasNext()){
			CircleGameObject g = (CircleGameObject) iter.next();
			float dist = (float) Math.hypot(g.px-px, g.py-py);
			if(dist<=distance){
				distance = dist;
				target = g;
			}
		}
		if(target!=null&&time<Global.seeker_bullet_targetting_time){
			float tx = target.px;
			float ty = target.py;
			dx = dx*(Global.seeker_bullet_targetting_time-time)/Global.seeker_bullet_targetting_time
					+(tx-px)/distance*time/Global.seeker_bullet_targetting_time*Global.seeker_bullet_acceleration*d;
			dy = dy*(Global.seeker_bullet_targetting_time-time)/Global.seeker_bullet_targetting_time
					+(ty-py)/distance*time/Global.seeker_bullet_targetting_time*Global.seeker_bullet_acceleration*d;
			float speed = (float) Math.hypot(dx, dy);
			if(speed>Global.seeker_bullet_maximum_speed){
				dx = dx*Global.seeker_bullet_maximum_speed/speed;
				dy = dy*Global.seeker_bullet_maximum_speed/speed;
			}else if(speed<Global.seeker_bullet_minimum_speed){
				dx = dx*Global.seeker_bullet_minimum_speed/speed;
				dy = dy*Global.seeker_bullet_minimum_speed/speed;			
			}
		}
	}
	public void move() {
		track();
		super.move();
	}

	public void checkPosition() {
		if (py > Global.height + Global.margin || py < -Global.margin
				|| px > Global.width + Global.margin || px < -Global.margin) {
			remove();
		}
	}
}
