package game.level;

import util.Global;
import events.Event;
import events.EventHandler;
import game.Game;
import game.lib.Enemies;

/**
 * 
 * Testing player movement, attacks, powerups, and a single enemy
 *
 */
public class TestLevel extends Level {

	@Override
	public void init() {
		Game.addEnemy(new Enemies.BulletEnemy(Global.gamewidth/2, Global.gameheight*3/4));
		for (int i = 0; i < 100; i++) {
			EventHandler.add(new Event(i * 500) {

				@Override
				public void run() {
					int rows = 100;
					for(int i=0;i< rows; i++){
						Game.addEnemy(new Enemies.SwarmEnemy(Global.gamewidth / rows * i, Global.gameheight+Global.margin));
					}
				}
			});
		}
	}

}
