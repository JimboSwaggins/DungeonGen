package main;

import java.util.ArrayList;

public class Material {
	private double density;
	private double temperature;
	private String color;
	private String name;
	private int valueMult;
	
	public int getMaterialValue() {
		return this.valueMult;
	}
	
	public String getName() {
		return this.name;
	}
	public Material(ArrayList<String> toInject) {
		while(toInject.size() > 0) {
			int toRemove = -1;
			for(int i = 0; i < toInject.size(); i++) {
				
				if(toInject.get(i).contains("VALUE:")) {
					this.valueMult = Integer.parseInt(toInject.get(i).replaceAll("VALUE:", ""));
					toRemove = i;
					break;
				}if(toInject.get(i).contains("COLOR:")) {
					this.color = toInject.get(i).replaceAll("COLOR:", "");
					toRemove = i;
					break;
				}
				if(toInject.get(i).contains("MATERIAL:")) {
					this.name = toInject.get(i).replaceAll("MATERIAL:", "");
					toRemove = i;
					break;
				}if(toInject.get(i).contains("DENSITY:")) {
					this.density = Double.parseDouble(toInject.get(i).replaceAll("DENSITY:", ""));
					toRemove = i;
					break;
				}if(toInject.get(i).contains("TEMP:")) {
					this.temperature = Double.parseDouble(toInject.get(i).replaceAll("TEMP:", ""));
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
		System.out.println("Successfully Loaded " + this.toString());
	}
	
	public String toString() {
		return name + " is a " + color + " material, with a density of " + density + ". It's current temperature is " + temperature;
	}
}
