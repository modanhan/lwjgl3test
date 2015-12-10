package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import game.Level;
import game.mode.GameMode;
import global.Global;

public class Console implements Runnable{
	public static BufferedReader in;
	public Console(){
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	@Override
	public void run() {
		do{
			try {
				while(!in.ready()&&Global.running==true){
					Thread.sleep(200);
				}
				String line = in.readLine();
				String[] args = line.split(" ");
				switch(args[0]){
				case "/cheats": //enables/disables cheats
					Global.cheats = !Global.cheats;
					System.out.println("Cheats "+Global.cheats!=null?Global.cheats?"on":"off":"off");
					break;
				case "/godmode": //enables/disables invincibility
					Global.godmode = !Global.godmode;
					System.out.println("Cheats "+Global.godmode!=null?Global.godmode?"on":"off":"off");
					break;
				case "/bulletstorm": //enables/disables rapid-fire
					Global.bulletstorm = !Global.bulletstorm;
					System.out.println("Cheats "+Global.bulletstorm!=null?Global.bulletstorm?"on":"off":"off");
					break;
				case "/power": //changes powerup level (1-10)
					if(GameMode.player!=null){
						GameMode.player.power = Integer.parseInt(args[1]);
						System.out.println("power set to "+args[1]);
					}else{
						System.out.println("player is dead");
					}
					break;
				case "/mode": //changes powerup type (0-1)
					if(GameMode.player!=null){
						GameMode.player.mode = Integer.parseInt(args[1]);
						System.out.println("mode set to "+args[1]);
					}else{
						System.out.println("player is dead");
					}
					break;
				case "/level": //changes current level (0-1)
					Level.level(Integer.parseInt(args[1]));
					System.out.println("level set to "+args[1]);
					break;
				case "/respawn": //respawns the player
					if(GameMode.player!=null)break;
					GameMode.resetPlayer();
					System.out.println("player respawned");
					break;
				case "/exit":
					return;
				default:
					System.out.println("\""+line+"\" not recognized." );
					break;
				}
			} catch (IOException | InterruptedException e) {
				return;
			}
		}while(true);
	}
}
