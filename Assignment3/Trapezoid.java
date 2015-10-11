/**
 * 
 * @author Matthew Meacham
 * 
 *         GET HIP - Assignment 2
 *
 */

// Represents a Trapezoid shape
public class Trapezoid extends Shape implements Printable {

	// One of the bases of the trapezoid
	private int baseA;
	// The other base of the trapezoid
	private int baseB;
	// The height of the trapezoid
	private int height;

	public Trapezoid(int baseA, int baseB, int height) {
		this.baseA = baseA;
		this.baseB = baseB;
		this.height = height;
		// Invoke the methods in the superclass to set the type and name of this trapezoid
		setShapeType("Quadrilateral");
		setShapeName("Trapezoid");
	}

	// Override the method inherited from Shape (which is implemented from the Printable interface) to handle custom description printing
	@Override
	public void printDescription() {
		// Invoke the super method because it contains the part that prints out the type, just for DRY purposes
		super.printDescription();
		System.out.println("Base A is " + baseA + " units");
		System.out.println("Base B is " + baseB + " units");
		System.out.println("Height is " + height + " units");

	}

	// Override the method inherited from Shape to calculate the area of a trapezoid
	@Override
	public double getArea() {
		// Area of a trapezoid is the average of the bases times the height
		return 0.5d * (baseA + baseB) * height;
	}
}
