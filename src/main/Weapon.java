package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Weapon {
	public String name;
	public String type;
	
	public int value;
	public Material material;
	public HashMap<Material, Integer> matsAndWeights;
	
	
	public int getWeaponValue() {
		return this.value * this.material.getMaterialValue();
	}
	
	public Weapon(ArrayList<String> toInject) {
		matsAndWeights  = new HashMap<Material, Integer>();
		while(toInject.size() > 0) {
			int toRemove = -1;
			for(int i = 0; i < toInject.size(); i++) {
				if(toInject.get(i).contains("TYPE:")) {
					this.type = toInject.get(i).replaceAll("TYPE:", "");
					toRemove = i;
					break;
				}if(toInject.get(i).contains("VALUE:")) {
					this.value = Integer.parseInt(toInject.get(i).replaceAll("VALUE:", ""));
					toRemove = i;
					break;
				}if(toInject.get(i).contains("WEAPON:")) {
					this.name = toInject.get(i).replaceAll("WEAPON:", "");
					toRemove = i;
					break;
				}
			}
			if(toRemove == -1) {
				break;
			}else {
				toInject.remove(toInject.get(toRemove));		
			}
		}
		try {
			this.material = LoadAllObjects.getMaterial("GOLD");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Loaded " + this.toString());
	}
	
	public String toString() {
		return "It is a " + this.name + " made of " + this.material.getName() + " with a value of " + getWeaponValue();
	}
	
	
}
