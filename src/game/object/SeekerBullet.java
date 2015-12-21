package game.object;

import java.util.List;
import java.util.ListIterator;

import util.Global;
import util.Time;

public abstract class SeekerBullet extends LinearBullet {
	public List<GameObject> targets;
	static final float ACCELERATION = 4.0f;
	static final float MINSPEED = 0.4f;
	static final float MAXSPEED = 0.4f;
	static final float TARGETTIME = 0.8f;
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
		if(target!=null&&time<TARGETTIME){
			float tx = target.px;
			float ty = target.py;
			dx = dx*(TARGETTIME-time)/TARGETTIME+(tx-px)/distance*time/TARGETTIME*d;
			dy = dy*(TARGETTIME-time)/TARGETTIME+(ty-py)/distance*time/TARGETTIME*d;
			dir = (float) Math.atan2(dy,dx);
			float speed = (float) Math.hypot(dx, dy);
			accelerate(ACCELERATION);
			if(speed>MAXSPEED){
				setSpeed(MAXSPEED);
			}else if(speed<MINSPEED){
				setSpeed(MINSPEED);
			}else{
				setSpeed(speed);
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
