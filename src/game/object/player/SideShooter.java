package game.object.player;

import util.Time;
import game.object.CircleGameObject;

/**
 * A object that orbits a specified player
 * @author Modan
 *
 */
public class SideShooter extends CircleGameObject {
	Player player;
	float dist, dir, speed;

	/**
	 * 
	 * @param player the player that utilizes the side shooter
	 * @param size the size
	 * @param dist the distance from the side shooter to its player
	 * @param dir the initial distance in randians, e.g. pi/2 is up
	 * @param speed the rotation speed
	 */
	public SideShooter(Player player, float size, float dist, float dir, float speed) {
		this.player = player;
		this.size = size;
		this.dist = dist;
		this.dir = dir;
		this.speed = speed;
		px = (float) (player.px + Math.cos(dir) * dist);
		py = (float) (player.py + Math.sin(dir) * dist);
	}

	@Override
	public void update() {
		dir+=speed*Time.getDelta();
		px = (float) (player.px + Math.cos(dir) * dist);
		py = (float) (player.py + Math.sin(dir) * dist);
	}
	
	@Override
	public void death() {
		// TODO Auto-generated method stub

	}

}
