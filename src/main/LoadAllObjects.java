package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import customException.MaterialNotFoundException;

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
	
	public static Material getMaterial(String toFind) throws MaterialNotFoundException{
		for(Material e:listOfMaterials) {
			if(e.getName().equalsIgnoreCase(toFind)) {
				System.out.println("Successfully retrieved material " + e.getName());
				return e;
			}
		}
		throw new MaterialNotFoundException();
		
	}

	
	public static void refreshDatabases() {
		listOfMaterials.clear();
		try {
			loadMaterials();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void printMaterials() {
		for(Material e:listOfMaterials) {
			Display.cprint(e.toString() + " \n");
		}
	}
	
	public static void loadItems() throws IOException{
		String fileName = ".\\tables\\ItemTable.txt";
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
				if(line.contains("ITEM:")) {
					isParsing = true;
				}
				
				//This begins loading the variables to prepare for injection
				if(isParsing) {
					weaponMats.add(line);
				}
				
				
				//This should initialize a new weapon type and then add the weapon to the array,
				//before beginning to create the next listed weapon.
				if(isParsing&&line.contains("/ITEM")) {
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
	
	public static void loadWeapons() throws IOException{
		String fileName = ".\\tables\\WeaponTable.txt";
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
		String fileName = ".\\tables\\MaterialTable.txt";

		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
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
