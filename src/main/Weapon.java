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
	
	
	public Material rollMaterial(ArrayList<String> toInject) throws IOException {
		boolean isParsing = false;
		ArrayList<Dual> matList = new ArrayList<Dual>();
		Material m = null;
		for(int i = 0; i < toInject.size(); i++) {
			if(toInject.get(i).contains("MATERIALS")) {
				isParsing = true;
				continue;
			}
			if(isParsing&&(toInject.get(i).contains("/WEAPON")||toInject.get(i).contains("/MATERIAL"))){
				isParsing = false;
				break;
			}if(isParsing){
				String[] a = toInject.get(i).split(":");
				matList.add(new Dual(a[0], Integer.parseInt(a[1].replaceAll(":", ""))));
			}
		}
		int max = matList.get(matList.size() - 1).getSlotTwo();
		int roll = RandomGenerator.randomInteger(max);
		for(int j = 0; j < matList.size() - 1;j++) {
			if(roll >= (int)matList.get(j).getSlotTwo()&&roll < (int)matList.get(j + 1).getSlotTwo()) {
					m =  LoadAllObjects.getMaterial(matList.get(j).getSlotOne());
			}
		}if(m == null) {
			m = LoadAllObjects.getMaterial(matList.get(matList.size() - 1).getSlotOne());
		}
		return m;
	}
 	public Weapon(ArrayList<String> toInject) {
		
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
			try {
				this.material = rollMaterial(toInject);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(toRemove == -1) {
				break;
			}else {
				toInject.remove(toInject.get(toRemove));		
			}
		}
		System.out.println("Successfully Loaded " + this.toString());
	}
	
	

	
	public String toString() {
		return "It is a " + this.name + " made of " + this.material.getName() + " with a value of " + getWeaponValue();
	}
	
	
}
