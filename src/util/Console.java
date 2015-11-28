package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import game.GlobalVars;

public class Console implements Runnable{
	public static BufferedReader in;
	public Console(){
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	@Override
	public void run() {
		do{
			try {
				while(!in.ready()&&GlobalVars.running==true){
					Thread.sleep(200);
				}
				String line = in.readLine();
				String[] args = line.split(" ");
				switch(args[0]){
				case "/cheats":
					GlobalVars.cheats = !GlobalVars.cheats;
					System.out.println("Cheats "+GlobalVars.cheats!=null?GlobalVars.cheats?"on":"off":"off");
					break;
				case "/godmode":
					GlobalVars.godmode = !GlobalVars.godmode;
					System.out.println("Cheats "+GlobalVars.godmode!=null?GlobalVars.godmode?"on":"off":"off");
					break;
				case "/bulletstorm":
					GlobalVars.bulletstorm = !GlobalVars.bulletstorm;
					System.out.println("Cheats "+GlobalVars.bulletstorm!=null?GlobalVars.bulletstorm?"on":"off":"off");
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
