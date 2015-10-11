/**
 * 
 * @author Matthew Meacham
 * 
 *         GET HIP - Assignment 2
 *
 */

// Represents a Rectangle shape
public class Rectangle extends Shape implements Printable {

	// The height of the rectangle
	private int height;
	// The width of the rectangle
	private int width;

	public Rectangle(int height, int width) {
		this.height = height;
		this.width = width;
		// Invoke the methods in the superclass to set the type and name of this rectangle
		setShapeType("Quadrilateral");
		setShapeName("Rectangle");
	}

	// Override the method inherited from Shape (which is implemented from the Printable interface) to handle custom description printing
	@Override
	public void printDescription() {
		// Invoke the super method because it contains the part that prints out the type, just for DRY purposes
		super.printDescription();
		System.out.println("Height is " + height + " units");
		System.out.println("Width is " + width + " units");

	}

	// Override the method inherited from Shape to calculate the area of a rectangle
	@Override
	public double getArea() {
		// Area of a rectangle is height * width
		return height * width;
	}
}
