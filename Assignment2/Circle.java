package com.matthew.shapes;

/**
 * 
 * @author Matthew Meacham
 * 
 *         GET HIP - Assignment 2
 *
 */

// Represents a Circle shape
public class Circle extends Shape implements Printable {

	// The radius of the circle
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
		// Invoke the methods in the superclass to set the type and name of this circle
		setShapeType("Circle");
		setShapeName("Circle");
	}

	// Override the method inherited from Shape (which is implemented from the Printable interface) to handle custom description printing
	@Override
	public void printDescription() {
		// Invoke the super method because it contains the part that prints out the type, just for DRY purposes
		super.printDescription();
		System.out.println("Radius is " + radius + " units");
	}

	// Override the method inherited from Shape to calculate the area of a circle
	@Override
	public double getArea() {
		// Area of a circle is PI * radius^2
		return Math.PI * radius * radius;
	}

}
