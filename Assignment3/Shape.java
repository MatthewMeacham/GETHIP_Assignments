/**
 * 
 * @author Matthew Meacham
 * 
 *         GET HIP - Assignment 2
 *
 */

// Represents a Shape
public abstract class Shape implements Printable {

	// The type of shape (Triangle, Quadrilateral, Circle)
	private String shapeType;
	// The name of the shape (Triangle, Rhombus, Rectangle, Trapezoid, etc.)
	private String shapeName;

	// Returns the type of the shape
	public String getShapeType() {
		return shapeType;
	}

	// Sets the shapeType to the type passed in as an argument
	public void setShapeType(String type) {
		shapeType = type;
	}

	// Returns the name of the shape
	public String getShapeName() {
		return shapeName;
	}

	// Sets the shapeName to the name passed in as an argument
	public void setShapeName(String name) {
		shapeName = name;
	}

	// Implements the method in the interface Printable to simply print the shape type
	public void printDescription() {
		System.out.println("Type is " + shapeType);
	}

	// An abstract method to calculate the area of the shape
	public abstract double getArea();

}
