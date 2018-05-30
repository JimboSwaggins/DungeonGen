package main;

public class Item {
	protected String name;
	protected String type;
	
	protected int value;
	protected int totalValue;
	
	
	public Material material;
	
	public QUALITY itemQuality;
	
	
	
	public int getTotalValue() {
		return this.value * this.material.getMaterialValue()  * this.getQualityMultiplier();
	}
	public int getValue() {
		return this.value;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public enum QUALITY{
		BAD, POOR, AVERAGE, QUALITY, MASTERCRAFTED
		
	}
	
	public String getValuetoString() {
		return "The " + this.name + "is worth " + this.getTotalValue() + " gold.";
	}
	
	public int getQualityMultiplier() {	
		switch(itemQuality) {	
		case BAD:
			return 1;
		case POOR:
			return 2;
		case AVERAGE: 
			return 4;
		case QUALITY:
			return 6;
		case MASTERCRAFTED:
			return 10;
		default:
			return -1;
				
		}
	}
}
