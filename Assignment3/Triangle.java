/**
 * 
 * @author Matthew Meacham
 * 
 * GET HIP - Assignment 2
 *
 */

//Represents a Triangle shape
public class Triangle extends Shape implements Printable {
	
	//The base of the triangle
	private int base;
	//The height of the triangle
	private int height;
	
	public Triangle(int base, int height) {
		this.base = base;
		this.height = height;
		// Invoke the methods in the superclass to set the type and name of this trapezoid
		setShapeType("Triangle");
		setShapeName("Triangle");
	}
	
	// Override the method inherited from Shape (which is implemented from the Printable interface) to handle custom description printing
	@Override
	public void printDescription() {
		// Invoke the super method because it contains the part that prints out the type, just for DRY purposes
		super.printDescription();
		System.out.println("Base is " + base + " units");
		System.out.println("Height is " + height + " units");
	}

	// Override the method inherited from Shape to calculate the area of a triangle
	@Override
	public double getArea() {
		//The area of a triangle is 0.5 * base * height
		return 0.5d * base * height;
	}
}
