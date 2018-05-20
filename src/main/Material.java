package main;

public class Material {
	private double density;
	private double temperature;
	private String color;
	private String name;
	
	public Material(String name, String color, double density, double temperature) {
		this.name = name;
		this.color = color;
		this.density = density;
		this.temperature = temperature;
	}
	
	public String toString() {
		return name + " is a " + color + " material, with a density of " + density + ". It's current temperature is " + temperature;
	}
}
