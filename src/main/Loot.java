package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loot {
	
	public String Name;
	public int value;
	public Furniture Parent;
	public enum TYPE{
		WEAPON, RING, NECKLACE, HEAD, ARMS, TORSO, LEGS, FEET, HANDS
		
		
	}
	
	public Loot(Furniture Parent) {
		this.Parent = Parent;
		
	}
	
	public static void loadPrefix(String file) throws IOException {

        // The name of the file to open.
        String fileName = file;

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	
	public void rollDynamicLoot() {
		
	}
}
