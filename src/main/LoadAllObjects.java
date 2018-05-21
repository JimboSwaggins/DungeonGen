package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadAllObjects {

	public String Name;
	public int value;
	public Furniture Parent;
	public static ArrayList<Material> listOfMaterials = new ArrayList<Material>();
	public enum TYPE{
		MATERIAL,
	}

	public LoadAllObjects() {
	}

	
	public static Material getMaterial(String toFind) throws IOException {
		for(Material e:listOfMaterials) {
			if(e.getName().equals(toFind)) {
				return e;
			}
		}
		return null;
	}

	public static void loadWeapons() throws IOException{
		String fileName = ".\\bin\\main\\WeaponTable.txt";
		String line = null;
		
		try {
			FileReader fileReader = 
					new FileReader(fileName);
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			boolean isParsing = false;
			ArrayList<String> weaponMats = new ArrayList<String>();
			while((line = bufferedReader.readLine()) != null) {
				//This begins reading for a new weapon type
				if(line.contains("WEAPON:")) {
					isParsing = true;
				}
				
				//This begins loading the variables to prepare for injection
				if(isParsing) {
					weaponMats.add(line);
				}
				
				
				//This should initialize a new weapon type and then add the weapon to the array,
				//before beginning to create the next listed weapon.
				if(isParsing&&line.contains("/WEAPON")) {
					isParsing = false;
					new Weapon(weaponMats);
					weaponMats.clear();
				}
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
	
	public static void loadMaterials() throws IOException {
		String fileName = ".\\bin\\main\\MaterialTable.txt";

		String line = null;

		try {
			FileReader fileReader = 
					new FileReader(fileName);
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			boolean isParsing = false;
			ArrayList<String> properties = new ArrayList<String>();
			while((line = bufferedReader.readLine()) != null) {
				//This begins reading for a new weapon type
				if(line.contains("MATERIAL:")) {
					isParsing = true;
				}
				
				//This begins loading the variables to prepare for injection
				if(isParsing) {
					properties.add(line);
				}
				
				
				//This should initialize a new weapon type and then add the weapon to the array,
				//before beginning to create the next listed weapon.
				if(isParsing&&line.contains("/MATERIAL")) {
					isParsing = false;
					listOfMaterials.add(new Material(properties));
					properties.clear();
				}
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

	public void rollDynamicLoot() {

	}
}
