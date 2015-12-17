package game.lib;

import events.EventHandler;
import game.Game;
import game.object.Enemy;
import game.ui.HealthBar;
import game.ui.UI;
import util.Global;

public class Enemies {
	public static class BasicEnemy extends Enemy {
		{
			hp = 20;
			size = 12;
		}

		public BasicEnemy(float px, float py) {
			super(px, py);
		}

		@Override
		public void update() {
			super.update();
		}

		public void render() {
			super.render();
		}
	}

	public static class ShooterEnemy extends Enemy {
		{
			hp = 20;
			size = 12;
		}

		public ShooterEnemy(float px, float py) {
			super(px, py);
		}

		public void spawn() {
			super.spawn();
			EventHandler.add(new EnemyShooterBulletEvent(1000));
		}

		@Override
		public void update() {
			super.update();
		}

		public void render() {
			super.render();
		}

		class EnemyShooterBulletEvent extends EnemyBulletEvent {

			public EnemyShooterBulletEvent(long time) {
				super(time);
			}

			@Override
			public void run() {
				if (hp <= 0)
					return;
				Game.addEnemyBullet(new EnemyBullet(px, py, Global.Dir.DOWN, Global.shooter_enemy_bullet_speed));
				EventHandler.add(new EnemyShooterBulletEvent(Global.shooter_enemy_bullet_delay));
			}
		}
	}

	public static class BossEnemy extends Enemy{
		HealthBar hpbar;
		boolean ready = false;
		{
			maxhp = 500;
			hp = maxhp;
			size = 24;
			speed = 0.1f;
		}
		public BossEnemy(float px, float py) {
			super(px, py);
			hpbar = new HealthBar(this);
		}
		public void spawn(){
			super.spawn();
			EventHandler.add(new EnemyBossBulletEvent1(4000));
			UI.addElement(hpbar);
		}
		@Override
		public void death() {
			super.death();
			UI.removeElement(hpbar);
		}
		@Override
		public void update() {
			super.update();
		}
		public void render(){
			super.render();
		}
		public void renderGlow(){
			super.render();
		}
		private static final float maxcount1 = 10;
		private static float count1 = maxcount1;
		private static final float maxcount2 = 20;
		private static float count2 = maxcount2;
		class EnemyBossBulletEvent1 extends EnemyBulletEvent {
			static final int rings = 24;
			public EnemyBossBulletEvent1(long time) {
				super(time);
			}
			@Override
			public void run() {
				if(hp<=0)return;
				for(float i=0;i<360f;i+=360f/rings){
					Game.addEnemyBullet(new EnemyBullet(px, py,(float) Math.toRadians(i+ count1*360f/rings/maxcount1),Global.enemy_bullet_default_speed));
				}
				if(count1>0){
					EventHandler.add(new EnemyBossBulletEvent1(400));
					count1-=1;
				}else{
					count1 = maxcount1;
					EventHandler.add(new EnemyBossBulletEvent2(4000));
				}
			}
		}
		class EnemyBossBulletEvent2 extends EnemyBulletEvent {
			static final int rings = 24;
			public EnemyBossBulletEvent2(long time) {
				super(time);
			}
			@Override
			public void run() {
				if(hp<=0)return;
				float dx = (float)Math.sin(Math.toRadians(360f*count2/maxcount2));
				float dy = (float)Math.cos(Math.toRadians(360f*count2/maxcount2));
				Game.addEnemyBullet(new EnemyBullet(px, py,(float) Math.toRadians(360f*count2/maxcount2),Global.enemy_bullet_default_speed));
				Game.addEnemyBullet(new EnemyBullet(px, py,(float) Math.toRadians(360f*count2/maxcount2),Global.enemy_bullet_default_speed));

				if(count2>0){
					EventHandler.add(new EnemyBossBulletEvent2(50));
					count2-=0.5f;
				}else{
					count2 = maxcount2;
					EventHandler.add(new EnemyBossBulletEvent1(4000));
				}
			}
		}
	}


}
