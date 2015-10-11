/**
 * 
 * @author Matthew Meacham
 * 
 * GET HIP - Assignment 2
 *
 */

//Represents a Rhombus shape
public class Rhombus extends Shape implements Printable {
	
	//One of the diagonals in a rhombus
	private int diagonalA;
	//The other diagonal in a rhombus
	private int diagonalB;
	
	public Rhombus(int diagonalA, int diagonalB) {
		this.diagonalA = diagonalA;
		this.diagonalB = diagonalB;
		//Invoke the methods in the superclass to set the type and name of this rhombus
		setShapeType("Quadrilateral");
		setShapeName("Rhombus");
	}
	
	//Override the method inherited from Shape (which is implemented from the Printable interface) to handle custom description printing
	@Override
	public void printDescription() {
		// Invoke the super method because it contains the part that prints out the type, just for DRY purposes
		super.printDescription();
		System.out.println("Diagonal A is " + diagonalA + " units");
		System.out.println("Diagonal B is " + diagonalB + " units");
	}

	//Override the method inherited from Shape to calculate the area of a rhombus
	@Override
	public double getArea() {
		//Area is (diagonalA * diagonalB) / 2
		return 0.5d * diagonalA * diagonalB;
	}
}
