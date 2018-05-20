package main;

public class InvalidMaterialConstraintException extends Exception{

	public InvalidMaterialConstraintException() {
		System.out.println("You appear to have entered an invalid parameter for a material");
	}
}
