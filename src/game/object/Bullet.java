package game.object;

import global.Global;
import util.Time;

public abstract class Bullet extends CircleGameObject{
	protected float dx, dy;
	protected float speed = 0.2f;
	protected void move(){
		int d = Time.getDelta();
		
		px += d*dx;
		py += d*dy;
	}
	protected void checkPosition(){
		if (py > Global.HEIGHT + Global.MARGIN|| py < -Global.MARGIN
				||px > Global.WIDTH + Global.MARGIN||px < - Global.MARGIN) {
			kill();
		}
	}
	public void update() {
		move();
		checkPosition();
	}
	
}
