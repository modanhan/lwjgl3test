package game;

import events.EventHandler;
import game.lib.Enemies;
import game.object.enemy.Enemy;
import mode.GameMode;
import util.Global;

public abstract class Level {/*
	public abstract void init();
	public abstract boolean isDone();
	public abstract void done();
	public static void level(int level){
		EventHandler.clear();
		GameMode.reset();
		if(GameMode.player!=null)GameMode.player.shoot();
		switch(level){
		case 0:
			GameMode.level = new Level.LevelZero();
			break;
		case 1:
			GameMode.level = new Level.LevelOne();
			break;
		default:
			GameMode.level = new Level.LevelZero();
			break;
		}
		GameMode.level.init();
	}
	static class LevelZero extends Level{
		Enemy boss1,boss2;
		@Override
		public void init() {
			boss1 = new Enemies.BossEnemy(Global.width*2/4, Global.height+100);
			boss2 = new Enemies.BossEnemy(Global.width*2/4, Global.height+100);
			boss1.spawn();
			boss2.spawn();
			new Enemy.EnemyMoveEvent(0,boss1,Global.width*1/4, Global.height*3/4).run();
			new Enemy.EnemyMoveEvent(0,boss2,Global.width*3/4, Global.height*3/4).run();

		}

		@Override
		public boolean isDone() {
			if(boss1.hp<=0&&boss2.hp<=0){
				boss1 = new Enemies.BossEnemy(Global.width*2/4, Global.height+100);
				boss2 = new Enemies.BossEnemy(Global.width*2/4, Global.height+100);
				boss1.spawn();
				boss2.spawn();
				new Enemy.EnemyMoveEvent(0,boss1,Global.width*1/4, Global.height*3/4).run();
				new Enemy.EnemyMoveEvent(0,boss2,Global.width*3/4, Global.height*3/4).run();
			}
			return false;
		}

		@Override
		public void done() {
			
		}
		
	}
	static class LevelOne extends Level{
		public Enemy boss;
		@Override
		public void init() {
			Enemy e1 = new Enemies.BasicEnemy(Global.width*2/4, Global.height*3/4);
			Enemy e2 = new Enemies.ShooterEnemy(Global.width*1/4, Global.height*3/4);
			Enemy e3 = new Enemies.SniperEnemy(Global.width*1/4, Global.height+100);
			Enemy e4 = new Enemies.SniperEnemy(Global.width*3/4, Global.height+100);
			Enemy e5 = new Enemies.SniperEnemy(Global.width*2/4, -100);
			boss = new Enemies.BossEnemy(Global.width*2/4, Global.height+100);
			e1.spawn();
			e2.spawn();
			EventHandler.add(new Enemy.EnemyDespawnEvent(4000,e2));
			EventHandler.add(new Enemy.EnemyMoveEvent(5000,e1,Global.width*2/4, Global.height+100));
			EventHandler.add(new Enemy.EnemySpawnEvent(5000,e3));
			EventHandler.add(new Enemy.EnemySpawnEvent(5000,e4));

			EventHandler.add(new Enemy.EnemyMoveEvent(5000,e3,Global.width*1/4, Global.height*3/4));
			EventHandler.add(new Enemy.EnemyMoveEvent(5000,e4,Global.width*3/4, Global.height*3/4));

			EventHandler.add(new Enemy.EnemyDespawnEvent(7000,e1));

			EventHandler.add(new Enemy.EnemySpawnEvent(10000,e5));
			EventHandler.add(new Enemy.EnemyMoveEvent(10000,e5,Global.width*2/4, Global.height*1/4));

			EventHandler.add(new Enemy.EnemyDespawnEvent(15000,e3));
			EventHandler.add(new Enemy.EnemyDespawnEvent(15000,e4));
			EventHandler.add(new Enemy.EnemyDespawnEvent(15000,e5));

			EventHandler.add(new Enemy.EnemySpawnEvent(15000,boss));
			EventHandler.add(new Enemy.EnemyMoveEvent(15000,boss,Global.width*2/4, Global.height*3/4));
		}

		@Override
		public boolean isDone() {
			if(boss.hp<=0){
				return true;
			}
			return false;
		}

		@Override
		public void done() {
			System.out.println("You win!");
			level(0);
			
		}

	}*/
}
