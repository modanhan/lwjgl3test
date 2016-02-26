package game.level;

import util.Global;
import util.Time;
import events.Event;
import events.EventHandler;
import game.Game;
import game.lib.Enemies;
import game.lib.Enemies.SniperSplashEnemy;
import game.object.SplineMovement;
import game.object.enemy.Enemy;
import game.object.powerup.Powerup;

/**
 * 
 * Testing player movement, attacks, powerups, and a single enemy
 *
 */
public class TestLevel extends Level {
	
	@Override
	public void init() {
//		Game.addEnemy(new Enemies.BulletEnemy(Global.gamewidth/2, Global.gameheight*3/4));
		
		int eventtime=0;
		
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemy s1=new Enemies.BulletEnemy(Global.gamewidth*.5f, Global.gamewidth*.8f){
					@Override
					public void death() {
						super.death();
						Game.addPowerup(new Powerup(x, y));
					}
				};
				s1.y=Global.gameheight*1.1f;
				s1.hp=10;
				Game.addEnemy(s1);
			}
		});
		
		eventtime+=7500;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemy s1=new Enemies.BulletEnemy(Global.gamewidth*.3f, Global.gameheight*.8f);
				s1.y=Global.gameheight*1.1f;
				
				Enemy s2=new Enemies.BulletEnemy(Global.gamewidth*.7f, Global.gameheight*.8f){
					public void death() {
						super.death();
						Game.addPowerup(new Powerup(x, y));
					}
				};
				s2.y=Global.gameheight*1.1f;
				
				s1.hp=s2.hp=10;
				Game.addEnemy(s1);
				Game.addEnemy(s2);
				
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));
			}
		});
		
		eventtime+=12500;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemy s1=new Enemies.BulletEnemy(Global.gamewidth*.3f, Global.gameheight*.6f){
					public void death() {
						super.death();
						Game.addPowerup(new Powerup(x, y));
					}
				};
				s1.y=Global.gameheight*1.1f;
				
				Enemy s2=new Enemies.BulletEnemy(Global.gamewidth*.7f, Global.gameheight*.6f){
					public void death() {
						super.death();
						Game.addPowerup(new Powerup(x, y));
					}
				};
				s2.y=Global.gameheight*1.1f;
				
				s1.hp=s2.hp=15;
				Game.addEnemy(s1);
				Game.addEnemy(s2);
				
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));
			}
		});
		
		
		eventtime+=12500;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemy s1=new Enemies.BulletEnemy(Global.gamewidth*.4f, Global.gameheight*.6f){
					public void death() {
						super.death();
						Game.addPowerup(new Powerup(x, y));
					}
				};
				s1.y=Global.gameheight*1.1f;
				
				Enemy s2=new Enemies.BulletEnemy(Global.gamewidth*.6f, Global.gameheight*.6f){
					public void death() {
						super.death();
						Game.addPowerup(new Powerup(x, y));
					}
				};
				s2.y=Global.gameheight*1.1f;
				
				s1.hp=s2.hp=25;
				Game.addEnemy(s1);
				Game.addEnemy(s2);
				
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));
			}
		});
		
		eventtime+=17500;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemy s1=new Enemies.BulletEnemy(Global.gamewidth*.1f, Global.gamewidth*.8f);
				s1.y=Global.gameheight*1.1f;
				Enemy s2=new Enemies.BulletEnemy(Global.gamewidth*.4f, Global.gamewidth*.8f);
				s2.y=Global.gameheight*1.1f;
				Enemy s3=new Enemies.BulletEnemy(Global.gamewidth*.6f, Global.gamewidth*.8f);
				s3.y=Global.gameheight*1.1f;
				Enemy s4=new Enemies.BulletEnemy(Global.gamewidth*.9f, Global.gamewidth*.8f);
				s4.y=Global.gameheight*1.1f;
				
				s1.x=s2.x=s3.x=s4.x=Global.gamewidth*.5f;
				s1.radius=s2.radius=s3.radius=s4.radius=25;
				s1.hp=s2.hp=s3.hp=s4.hp=15;
				
				Game.addEnemy(s1);
				Game.addEnemy(s2);
				Game.addEnemy(s3);
				Game.addEnemy(s4);
				
				Game.addPowerup(new Powerup(Global.gamewidth*.1f, Global.gameheight*1.1f));
			}
		});
		
		eventtime+=10000;
		EventHandler.add(new Event(eventtime) {
			
			public void run() {
				Game.addPowerup(new Powerup(Global.gamewidth*.9f, Global.gameheight*1.1f));
			}
		});
		
		eventtime+=5000;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemy s1=new Enemies.SniperEnemy(Global.gamewidth*.25f, Global.gameheight/2),s2=new Enemies.SniperEnemy(Global.gamewidth*.75f, Global.gameheight/2);
				SplineMovement.add(s1, 0-s1.radius, 0-s1.radius, Global.gamewidth, 0, Global.gamewidth, Global.gameheight, Global.gamewidth*.25f, Global.gameheight/2, 10000);
				SplineMovement.add(s2,  Global.gamewidth+s1.radius, 0-s1.radius,0, 0,0, Global.gameheight, Global.gamewidth*.75f, Global.gameheight/2,  10000);
				
				s1.hp=s2.hp=50;
				
				Game.addEnemy(s1);
				Game.addEnemy(s2);
			}
		});
		
		eventtime+=7500;
		EventHandler.add(new Event(eventtime) {
			public void run() {
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));
			}
		});

		eventtime+=2500;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemies.SniperEnemy s1=new Enemies.SniperEnemy(Global.gamewidth*.25f, Global.gameheight/4){
					float ptheta=0,pr=0,pt=0;
					@Override
					protected void move() {
						pt+=Time.getDelta();
						if(pt>=10000&&pr<150){
							pr+=(float)Time.getDelta()/50;
						}
						if(pr>150)pr=150;
						ptheta+=(float)Time.getDelta()/1000;
						while(ptheta>Global.Dir.PI2){
						ptheta-=Global.Dir.PI2;}
						x=(float) Math.sin(ptheta)*pr+tx;
						y=(float) Math.cos(ptheta)*pr+ty;
					}
				},s2=new Enemies.SniperEnemy(Global.gamewidth*.75f, Global.gameheight/4){
					float ptheta=0,pr=0,pt=0;
					@Override
					protected void move() {
						pt+=Time.getDelta();
						if(pt>=10000&&pr<150){
							pr+=(float)Time.getDelta()/50;
						}
						if(pr>150)pr=150;
						ptheta+=(float)Time.getDelta()/1000;
						while(ptheta>Global.Dir.PI2){
						ptheta-=Global.Dir.PI2;}
						x=(float) -Math.sin(ptheta)*pr+tx;
						y=(float) Math.cos(ptheta)*pr+ty;
					}
				};
				s1.delay = s2.delay = 500; 
				s1.maxhp = s2.maxhp = s1.hp= s2.hp = 75;

				SplineMovement.add(s1, 0 - s1.radius, Global.gameheight + s1.radius, Global.gamewidth, 0,
						Global.gamewidth, Global.gameheight, Global.gamewidth * .25f, Global.gameheight / 4, 10000);
				Game.addEnemy(s1);
				SplineMovement.add(s2, Global.gamewidth + s1.radius, Global.gameheight + s1.radius, 0, 0, 0,
						Global.gameheight, Global.gamewidth * .75f, Global.gameheight / 4, 10000);
				Game.addEnemy(s2);
			}
		});
		
		eventtime+=5000;
		EventHandler.add(new Event(eventtime) {
			public void run() {
				Game.addPowerup(new Powerup(Global.gamewidth * .1f, Global.gameheight * 1.1f));
				Game.addPowerup(new Powerup(Global.gamewidth * .9f, Global.gameheight * 1.1f));
			}
		});
		
		
		eventtime+=10000;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemies.SniperSplashEnemy s1 = new SniperSplashEnemy(Global.gamewidth / 2, Global.gameheight * .8f,2,.2f);
				s1.x=Global.gamewidth/2;
				s1.y=Global.gameheight*1.1f;
				s1.hp=125;
				Game.addEnemy(s1);
			}
		});
		
		eventtime+=2500;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemies.SniperSplashEnemy s1 = new SniperSplashEnemy(Global.gamewidth *.3f, Global.gameheight * .2f,2,.2f);
				s1.x=-Global.gamewidth*.1f;
				s1.y=-Global.gameheight*.1f;
				s1.hp=125;
				Game.addEnemy(s1);
			}
		});
		
		eventtime+=2500;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));
				Enemies.SniperSplashEnemy s1 = new SniperSplashEnemy(Global.gamewidth *.7f, Global.gameheight * .2f,2,.2f);
				s1.x=Global.gamewidth*1.1f;
				s1.y=-Global.gameheight*.1f;
				s1.hp=125;
				Game.addEnemy(s1);
			}
		});
		
		eventtime+=30000;
		EventHandler.add(new Event(eventtime) {
			@Override
			public void run() {
				Enemies.SniperSplashEnemy s1 = new SniperSplashEnemy(Global.gamewidth *.2f, Global.gameheight * .2f,2,.2f);
				Enemies.SniperSplashEnemy s2 = new SniperSplashEnemy(Global.gamewidth *.2f, Global.gameheight * .8f,2,.2f);
				Enemies.SniperSplashEnemy s3 = new SniperSplashEnemy(Global.gamewidth *.8f, Global.gameheight * .8f,2,.2f);
				Enemies.SniperSplashEnemy s4 = new SniperSplashEnemy(Global.gamewidth *.8f, Global.gameheight * .2f,2,.2f);
				s1.x=s1.y=s2.x=s4.y=Global.gamewidth*-.1f;
				s2.y=s4.x=s3.x=s3.y=Global.gamewidth*1.1f;
				s1.hp=s2.hp=s3.hp=s4.hp=80;
				s1.radius=s2.radius=s3.radius=s4.radius=35;
				Game.addEnemy(s1);
				Game.addEnemy(s2);
				Game.addEnemy(s3);
				Game.addEnemy(s4);
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));
			}
		});
		
		eventtime+=5000;
		EventHandler.add(new Event(eventtime) {
			
			@Override
			public void run() {
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));				
			}
		});
		
		eventtime+=25000;
		EventHandler.add(new Event(eventtime) {
			
			class RotatingSniperSplashEnemy extends Enemies.SniperSplashEnemy{

				float costheta=(float) Math.cos(.004),
						sintheta=(float) Math.sin(.004);
				
				public RotatingSniperSplashEnemy(float x, float y, int n, float coneangle) {
					super(x, y, n, coneangle);
				}
				
				protected void move() {
					float tdx=Global.gamewidth*.5f,tdy=Global.gamewidth*.5f;
					tx-=tdx;
					ty-=tdy;
					float txp=costheta*tx-sintheta*ty,
							typ=sintheta*tx+costheta*ty;
					tx=txp;
					ty=typ;
					tx+=tdx;
					ty+=tdy;
					x=tx;y=ty;
				}
				
			}
			
			public void run() {
				RotatingSniperSplashEnemy s1 = new RotatingSniperSplashEnemy(Global.gamewidth *.2f, Global.gameheight * .2f,2,.2f);
				RotatingSniperSplashEnemy s2 = new RotatingSniperSplashEnemy(Global.gamewidth *.2f, Global.gameheight * .8f,2,.2f);
				RotatingSniperSplashEnemy s3 = new RotatingSniperSplashEnemy(Global.gamewidth *.8f, Global.gameheight * .8f,2,.2f);
				RotatingSniperSplashEnemy s4 = new RotatingSniperSplashEnemy(Global.gamewidth *.8f, Global.gameheight * .2f,2,.2f);
			//	s1.x=s1.y=s2.x=s4.y=Global.gamewidth*-.1f;
			//	s2.y=s4.x=s3.x=s3.y=Global.gamewidth*1.1f;
				s1.hp=s2.hp=s3.hp=s4.hp=125;
				s1.radius=s2.radius=s3.radius=s4.radius=10;
				Game.addEnemy(s1);
				Game.addEnemy(s2);
				Game.addEnemy(s3);
				Game.addEnemy(s4);
				Game.addPowerup(new Powerup(Global.gamewidth*.5f, Global.gameheight*1.1f));
			}
		});

	}

}
