package game;

import util.Time;

public abstract class Bullet extends GameEntity{
	protected float dx, dy;
	protected float speed = 0.2f;
	protected void move(){
		int d = Time.getDelta();
		
		px += d*dx;
		py += d*dy;
	}
	protected void checkPosition(){
		if (py > GlobalVars.HEIGHT + GlobalVars.MARGIN|| py < -GlobalVars.MARGIN
				||px > GlobalVars.WIDTH + GlobalVars.MARGIN||px < - GlobalVars.MARGIN) {
			kill();
		}
	}
	public void update() {
		move();
		checkPosition();
	}
	
}
