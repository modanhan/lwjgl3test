package game.object;

import util.Global;
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
		if (py > Global.height + Global.margin|| py < -Global.margin
				||px > Global.width + Global.margin||px < - Global.margin) {
			kill();
		}
	}
	public void update() {
		move();
		checkPosition();
	}
	
}
