package main;

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
	
	
	public void rollDynamicLoot() {
		
	}
}
