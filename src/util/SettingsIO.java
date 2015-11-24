package util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * Writes/Reads settings in a file called "settings.ser"
 * 
 */

public class SettingsIO {

	/*
	 * takes in int array containing settings of format
	 * [fullscreen, length, width]
	 * 
	 * note: fullscreen = 0, !fullscreen
	 */
	public static void write(int[] fields){
		try {
			// get current working dir
			File dir = new File(System.getProperty("user.dir"));
			FileOutputStream fileout = new FileOutputStream(dir+"/settings.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(fields);
			out.close();
			fileout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int[] read(){
		int [] fields = new int[3];
		try {
			FileInputStream filein = new FileInputStream("settings.ser");
			ObjectInputStream in = new ObjectInputStream(filein);
			// cast input into int[]
			fields = (int[])in.readObject();
			in.close();
			filein.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error in reading object from ser file");;
		}
		
		return fields;
	}

}
