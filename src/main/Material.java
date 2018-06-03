package main;

import java.util.ArrayList;

import utils.StringAnalyzer;

public class Material {
	private double density;
	private double temperature;
	private String color;
	private String name;
	private int valueMult;
	
	
	public double getDensity() {
		return this.density;
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
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
					if(!toInject.get(i).replaceAll("MATERIAL:", "").equals(null)) {
						this.name = toInject.get(i).replaceAll("MATERIAL:", "");
						toRemove = i;
					}
					break;
				}if(toInject.get(i).contains("DENSITY:")) {
					String temp = toInject.get(i).replaceAll("DENSITY:", "");
					if(!temp.equals("")&&!StringAnalyzer.containsCharacters(temp)) {
						this.density = Double.parseDouble(temp);
						toRemove = i;
					}else {
						this.density = Integer.MIN_VALUE + 1;
					}
					break;
				}if(toInject.get(i).contains("TEMP:")) {
					String temp = toInject.get(i).replaceAll("TEMP:", "");
					if(!temp.equals("")&&!StringAnalyzer.containsCharacters(temp)) {
						this.temperature = Double.parseDouble(temp);
						toRemove = i;
					}else {
						this.temperature = Integer.MIN_VALUE + 1;
					}
					break;
				}
			}
			if(toRemove == -1) {
				break;
			}else {
				toInject.remove(toInject.get(toRemove));		
			}
			
			checkOverNullValues();
		}
		//Display.getConsole().append(this.toString() + "\n");
	}
	
	public void checkOverNullValues() {
		if(this.name == null) {
			this.name = "MISSING NAME";
		}if(this.color == null) {
			this.color = "MISSING COLOR";
		}if(this.valueMult == 0) {
			this.valueMult = Integer.MIN_VALUE + 1;
		}
	}
	
	public String toString() {
		return name + " is a " + color + " material, with a density of " + density + ". It's current temperature is " + temperature;
	}
}
