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
		MATERIAL,


	}

	public Loot(Furniture Parent) {
		this.Parent = Parent;

	}

	public void loadMaterial(String toFind) throws IOException {
		String fileName = ".\\bin\\main\\LootTable.txt";
		String line = null;

		try {
			FileReader fileReader = 
					new FileReader(fileName);
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			Material m = null;
			while((line = bufferedReader.readLine()) != null) {
				if(line.contains("[MATERIAL:" + toFind.toUpperCase())) {
					m = toMaterial(line);
					break;
				}
			} 

			bufferedReader.close(); 
			if(m == null) {
				System.out.println("ERROR: MATERIAL NOT FOUND");
			}else {
				System.out.println(m.toString());
			}
		}catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		} 
	}

	public void loadPrefix() throws IOException {
		String fileName = ".\\bin\\main\\LootTable.txt";

		String line = null;

		try {
			FileReader fileReader = 
					new FileReader(fileName);
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				getType(line);
			} 
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" 	+ fileName + "'");
		}
	}

	public String getType(String toFind) {
		int end = toFind.indexOf("]");
		String toRead = toFind.substring(0, end + 1);
		toFind = toFind.substring(end, toFind.length() -1);
		if(!toRead.equals("")) {
			System.out.println(toRead);
			if(toRead.contains("]")) {
				toRead = toRead.replaceAll("]", "");
			}
			if(toRead.contains("MATERIAL")){
				String[] properties = toRead.split(":");
				Material mat = new Material(properties[1], properties[2], Double.parseDouble(properties[3]), Double.parseDouble(properties[4]));
			}
		}
		return toFind;
	}

	public Material toMaterial(String toConvert) {
		toConvert = toConvert.replace("]","");
		String[] properties = toConvert.split(":");
		Material mat = new Material(properties[1], properties[2], Double.parseDouble(properties[3]), Double.parseDouble(properties[4]));
		System.out.println(mat.toString());
		return mat;
	}
	public void rollDynamicLoot() {

	}
}
