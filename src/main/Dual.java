package main;

public class Dual {
	private final String slotOne;
	private final int slotTwo;
	
	
	public String getSlotOne() {
		return this.slotOne;
	}
	
	public int getSlotTwo() {
		return this.slotTwo;
	}
	
	public Dual(String one, int two) {
		this.slotOne = one;
		this.slotTwo = two;
	}
}
