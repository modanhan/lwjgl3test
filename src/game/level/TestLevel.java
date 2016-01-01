package game.level;

import util.Global;
import events.Event;
import events.EventHandler;
import game.Game;
import game.lib.Enemies;
import game.object.powerup.Powerup;

/**
 * 
 * Testing player movement, attacks, powerups, and a single enemy
 *
 */
public class TestLevel extends Level {

	@Override
	public void init() {
		Game.addEnemy(new Enemies.DPSCalcEnemy(300, 300));
		for (int i = 0; i < 100; i++) {
			EventHandler.add(new Event(i * 2500) {

				@Override
				public void run() {
					Game.addPowerup(new Powerup(Global.gamewidth / 2, Global.gameheight));

				}

			});
		}
	}

}
