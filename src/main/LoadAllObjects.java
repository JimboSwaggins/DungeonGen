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
	public ArrayList<Material> listOfMaterials;
	public enum TYPE{
		MATERIAL,
	}

	public LoadAllObjects() {
		listOfMaterials = new ArrayList<Material>();
	}

	
	
	
	public static void loadMaterial(String toFind) throws IOException {
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

	public static void loadWeapons() throws IOException{
		String fileName = ".\\bin \\main\\WeaponTable.txt";
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

	public static String getType(String toFind) {
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

	public static Material toMaterial(String toConvert) {
		toConvert = toConvert.replace("]","");
		String[] properties = toConvert.split(":");
		Material mat = new Material(properties[1], properties[2], Double.parseDouble(properties[3]), Double.parseDouble(properties[4]));
		System.out.println(mat.toString());
		return mat;
	}
	public void rollDynamicLoot() {

	}
}
