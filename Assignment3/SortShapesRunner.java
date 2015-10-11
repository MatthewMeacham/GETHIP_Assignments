import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Matthew Meacham
 * 
 *         GET HIP - Assignment 3
 * 
 *         Includes a Selection Sort, Insertion Sort, Bubble Sort, Shell Sort, Bidirectional Bubble Sort (Cocktail Sort), 
 *         Comb Sort, Gnome Sort, Odd-Even Sort, Stooge Sort, Cycle Sort, and Bitonic Sort 
 *         
 *         Also reads the shapes from the shapes.csv file
 *
 */
public class SortShapesRunner {

	public SortShapesRunner() {
		// Initialize the List of shapes
		List<Shape> shapes = readFromFile("shapes.csv");

		// Call each sorting method on these data
		bubbleSort(shapes);
		selectionSort(shapes);
		insertionSort(shapes);
		shellSort(shapes);
		bidirectionalBubbleSort(shapes);
		combSort(shapes);
		gnomeSort(shapes);
		oddEvenSort(shapes);
		stoogeSort(shapes, 0, shapes.size() - 1);
		cycleSort(shapes);

		// Use regular arrays for the bitonic sort because the way Lists work with the references will cause issues
		// I mean the convenience methods with arrays are much better for this
		Shape[] shapesArray = new Shape[shapes.size()];
		for (int i = 0; i < shapes.size(); i++) {
			shapesArray[i] = shapes.get(i);
		}
		bitonicSort(true, shapesArray);

		// Switch "shapes" to shapesArray if you would like to see the bitonic sort output (they're all the same)
		for (Shape shape : shapes) {
			shape.printDescription();
			System.out.printf("The area is %.2f\n", shape.getArea());
			System.out.println();
		}
	}

	private List<Shape> readFromFile(String fileName) {
		// Initialize an empty array for the shapes and a reader to read from the given file
		List<Shape> shapes = new ArrayList<Shape>();
		BufferedReader reader = null;
		try {
			// Create the BufferedReader from a FileReader that takes in the fileName parameter
			reader = new BufferedReader(new FileReader(fileName));

			// Name,Type,BaseA,BaseB,Height,DiagonalA,DiagonalB,Radius

			// While there is another line in the file
			String line = reader.readLine();
			while (line != null) {
				// Since it is a CSV file, we split the line at its commas
				String[] strings = line.split(",");
				// Check the Name (which is at index 0) and then create the correct shape off of that
				// Catch the ArrayIndexOfOutBoundsException in case the user forgets a comma and we try to access a non-existent array
				try {
					switch (strings[0]) {
					case "Circle":
						shapes.add(new Circle(Integer.valueOf(strings[7])));
						break;
					case "Triangle":
						shapes.add(new Triangle(Integer.valueOf(strings[2]), Integer.valueOf(strings[4])));
						break;
					case "Rectangle":
						shapes.add(new Rectangle(Integer.valueOf(strings[2]), Integer.valueOf(strings[4])));
						break;
					case "Trapezoid":
						shapes.add(new Trapezoid(Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4])));
						break;
					case "Rhombus":
						shapes.add(new Rhombus(Integer.valueOf(strings[5]), Integer.valueOf(strings[6])));
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// Just do nothing, the shape won't get added
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the reader
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return shapes;
	}

	/**
	 * Bubble sort
	 */

	// Uses a bubble sort, runtime complexity of O(n^2)
	// Java is pass by reference, so there is no need to return the sorted list, instead we just sort the list itself
	private void bubbleSort(List<Shape> shapes) {
		// operation is a variable indicating whether or not the shapes List has been edited
		boolean operation;
		do {
			operation = false;
			for (int i = 0; i < shapes.size() - 1; i++) {
				// if the area of the shape at index i is greater than that of index i + 1, then swap the two
				if (shapes.get(i).getArea() > shapes.get(i + 1).getArea()) {
					// swap the elements
					Shape tempShape = shapes.get(i + 1);
					shapes.set(i + 1, shapes.get(i));
					shapes.set(i, tempShape);
					operation = true;
				}
			}
		} while (operation);
	}

	/**
	 * Bidirectional Bubble Sort (Cocktail Sort)
	 */

	// This is simply a bubble sort that goes both ways, left to right, right to left, left to right, right to left....
	// Runtime complexity is O(n^2)
	private void bidirectionalBubbleSort(List<Shape> shapes) {
		int start = -1, end = shapes.size();
		// operation indicates whether or not the List has been edited
		boolean operation;
		do {
			operation = false;

			// Start at the right side
			start++;
			for (int i = end - 1; i > start; i--) {
				if (shapes.get(i).getArea() < shapes.get(i - 1).getArea()) {
					// Swap them
					Shape tempShape = shapes.get(i);
					shapes.set(i, shapes.get(i - 1));
					shapes.set(i - 1, tempShape);
					operation = true;
				}
			}

			// Then start at left side
			end--;
			for (int i = start; i < end; i++) {
				if (shapes.get(i).getArea() > shapes.get(i + 1).getArea()) {
					// Swap them
					Shape tempShape = shapes.get(i);
					shapes.set(i, shapes.get(i + 1));
					shapes.set(i + 1, tempShape);
					operation = true;
				}
			}
		} while (operation);
	}

	/**
	 * Insertion sort
	 */

	// Inserts the element into its correct place in a continually being-sorted list
	// Insertion sort has a runtime complexity of O(n^2)
	private void insertionSort(List<Shape> shapes) {
		for (int i = 1; i < shapes.size(); i++) {
			double area = shapes.get(i).getArea();
			for (int j = 0; j < shapes.size(); j++) {
				if (shapes.get(j).getArea() > area) {
					shapes.add(j, shapes.remove(i));
					break;
				}
			}
		}
	}

	/**
	 * Selection sort
	 */

	// Selects the smallest area in the list and then places into the place it belongs in a sorted list
	// Selection sort has a runtime complexity of O(n^2)
	private void selectionSort(List<Shape> shapes) {
		for (int i = 0; i < shapes.size(); i++) {
			double area = shapes.get(i).getArea();
			int indexOfLeast = i;
			for (int j = i; j < shapes.size(); j++) {
				if (shapes.get(j).getArea() <= area) {
					indexOfLeast = j;
					area = shapes.get(j).getArea();
				}
			}
			Shape shape = shapes.remove(indexOfLeast);
			shapes.add(i, shape);
		}
	}

	/**
	 * Comb sort
	 */

	// This is a fun sort that eliminates small values that are at the end of the List and then performs a bubble sort essentially
	// It is crucial that small values be eliminated from the end because they slow down the bubble sort tremendously
	private void combSort(List<Shape> shapes) {
		// How much we will "shrink" the List size by to determine the gap, 1.3 is the suggested value, any bigger and not enough comparisons
		// will take place, too small and too little comparisons will be made
		float shrink = 1.3f;
		// gap is the distance between indexes to check
		int gap = shapes.size();
		// operation represents if or if not the List has been operated on ("mutated")
		boolean operation = false;
		// While the gap is still greater than 1 or the List has been operated on do the sorting stuff
		while ((gap > 1) || operation) {
			// Keep making the gap smaller as long as its greater than 1
			if (gap > 1) {
				gap = (int) ((float) gap / shrink);
			}

			operation = false;
			// Now actually sort it
			for (int i = 0; gap + i < shapes.size(); i++) {
				if (shapes.get(i).getArea() > shapes.get(i + gap).getArea()) {
					// Swap the elements because the one at index i is greater than the one at index i + gap
					Shape tempShape = shapes.get(i);
					shapes.set(i, shapes.get(i + gap));
					shapes.set(i + gap, tempShape);
					operation = true;
				}
			}
		}
	}

	/**
	 * Gnome Sort
	 */

	// Finds the first place where two adjacent elements are out of order and swaps them, kind of like a bubble sort
	// Has a runtime complexity of O(n^2)
	private void gnomeSort(List<Shape> shapes) {
		int index = 1;
		int last = 0;
		// While we are not outside of the upper bound of the array sort the elements
		while (index < shapes.size()) {
			if (shapes.get(index).getArea() > shapes.get(index - 1).getArea()) {
				if (last != 0) {
					index = last;
					last = 0;
				}
				index++;
			} else {
				// swap the elements
				Shape tempShape = shapes.get(index);
				shapes.set(index, shapes.get(index - 1));
				shapes.set(index - 1, tempShape);
				if (index > 1) {
					if (last == 0) last = index;
					index--;
				} else index++;
			}
		}
	}

	/**
	 * Odd-Even Sort
	 */
	// This one works just like a bubble sort except instead of comparing adjacent pairs, it compares odd and even adjacent indexes
	// This one has a runtime complexity of O(n^2)
	private void oddEvenSort(List<Shape> shapes) {
		boolean operation;
		do {
			operation = false;
			// Start with evens, increment by two because we need to stay on even numbers
			for (int i = 0; i < shapes.size() - 1; i += 2) {
				if (shapes.get(i).getArea() > shapes.get(i + 1).getArea()) {
					// Swap the two elements because the first one was bigger than the second
					Shape tempShape = shapes.get(i);
					shapes.set(i, shapes.get(i + 1));
					shapes.set(i + 1, tempShape);
					operation = true;
				}
			}
			// Now do it with odds, increment by two because we need to stay with odd numbers
			for (int i = 1; i < shapes.size() - 1; i += 2) {
				if (shapes.get(i).getArea() > shapes.get(i + 1).getArea()) {
					// Swap the two elements because the first one was bigger than the second
					Shape tempShape = shapes.get(i);
					shapes.set(i, shapes.get(i + 1));
					shapes.set(i + 1, tempShape);
					operation = true;
				}
			}
		} while (operation);
	}

	/**
	 * Stooge sort
	 */

	// This one is funny. It works by comparing the area at the end with the area at the beginning and if the one at the beginning is
	// greater than the area of the one at the end, then it swaps them, then if there is more than 2 elements in the List, then it does that
	// again with the first 2/3 of the List, then the last 2/3 of the list, then the first 2/3 of the list again
	// It has a runtime complexity of O(n^(log 3 / log 1.5)) = O(n^2.7095...)
	private void stoogeSort(List<Shape> shapes, int start, int end) {
		if (shapes.get(end).getArea() < shapes.get(start).getArea()) {
			// Swap
			Shape tempShape = shapes.get(start);
			shapes.set(start, shapes.get(end));
			shapes.set(end, tempShape);
		}
		if ((end - start + 1) > 2) {
			int splitAmount = (int) (Math.ceil((end - start + 1) / 3));
			stoogeSort(shapes, start, end - splitAmount);
			stoogeSort(shapes, start + splitAmount, end);
			stoogeSort(shapes, start, end - splitAmount);
		}
	}

	/**
	 * Cycle sort
	 */

	// Cycle sort minimizes the number of writes that have to be made to the array
	// It works essentially by counting the number of elements that are greater than the starting element and then moves it to that position
	// Has a runtime complexity of O(n^2)
	private void cycleSort(List<Shape> shapes) {
		for (int cycleStart = 0; cycleStart < shapes.size() - 1; cycleStart++) {
			Shape shape = shapes.get(cycleStart);
			int pos = cycleStart;
			// This will count the number of shapes with areas bigger than the shape area from the cycleStart
			for (int i = cycleStart + 1; i < shapes.size(); i++) {
				if (shapes.get(i).getArea() < shape.getArea()) pos++;
			}

			// If the pos is equal to the cycleStart, there were not shapes with areas greater than the shape area, so just go to the next iteration
			if (pos == cycleStart) continue;

			// Skip duplicates
			while (shape.getArea() == shapes.get(pos).getArea())
				pos++;

			// Set the element at pos to shape, and then set shape to the shape that was just there
			Shape tempShape = shapes.get(pos);
			shapes.set(pos, shape);
			shape = tempShape;

			while (pos != cycleStart) {
				pos = cycleStart;
				// This will count the number of shapes with areas bigger than the shape area from the cycleStart
				for (int i = cycleStart + 1; i < shapes.size(); i++) {
					if (shapes.get(i).getArea() < shape.getArea()) pos++;
				}

				// Skip duplicates
				while (shape.getArea() == shapes.get(pos).getArea())
					pos++;

				// Set the element at pos to shape, and then set shape to the shape that was just there
				tempShape = shapes.get(pos);
				shapes.set(pos, shape);
				shape = tempShape;
			}
		}
	}

	/**
	 * Shell sort
	 */

	// Adapted from the code on RosettaCode (http://rosettacode.org/wiki/Sorting_algorithms/Shell_sort#Java)
	// Runtime complexity is O(n^(3/2))
	private void shellSort(List<Shape> shapes) {
		int increment = shapes.size() / 2;
		while (increment > 0) {
			for (int i = increment; i < shapes.size(); i++) {
				int j = i;
				Shape tempShape = shapes.get(i);
				while (j >= increment && shapes.get(j - increment).getArea() > tempShape.getArea()) {
					shapes.set(j, shapes.get(j - increment));
					j = j - increment;
				}
				shapes.set(j, tempShape);
			}
			if (increment == 2) {
				increment = 1;
			} else {
				increment *= (5.0 / 11);
			}
		}
	}

	/**
	 * Bitonic Sort
	 */

	// Translated from the Python code found on Wikipedia (https://en.wikipedia.org/wiki/Bitonic_sorter)
	// The runtime complexity (if my convenience methods are not included) is O(nlog(n)^2)
	// It is a recursive function that constantly sorts two smaller and smaller arrays and then merges them
	private Shape[] bitonicSort(final boolean up, Shape[] shapes) {
		if (shapes.length <= 1) return shapes;
		// Sort the first half of the array ascendingly
		Shape[] first = bitonicSort(true, copyOfRange(shapes, 0, shapes.length / 2));
		// Sort the second half of the array descendingly
		Shape[] second = bitonicSort(false, copyOfRange(shapes, shapes.length / 2, shapes.length));
		return bitonicMerge(up, combine(first, second));
	}

	// This method takes the new combined arrays and then continually sorts the elements
	private Shape[] bitonicMerge(final boolean up, Shape[] shapes) {
		if (shapes.length <= 1) return shapes;
		// Call the compare method to actually handle sorting
		bitonicCompare(up, shapes);
		// Call the merge on the first half of the array (which also sorts it)
		Shape[] first = bitonicMerge(up, copyOfRange(shapes, 0, shapes.length / 2));
		// Call the merge on the second half of the array (which also sorts it)
		Shape[] second = bitonicMerge(up, copyOfRange(shapes, shapes.length / 2, shapes.length));
		// Combine the first and second and return them
		return combine(first, second);
	}

	// This method actually handles the comparisons, it compares element i with elements i + size / 2
	private void bitonicCompare(final boolean up, Shape[] shapes) {
		// Distance represents the middle of the array, simply the length / 2
		int distance = shapes.length / 2;
		for (int i = 0; i < distance; i++) {
			// If the area of the shape at i is greater than that of i + distance is equal to up, then swap the two elements
			if ((shapes[i].getArea() > shapes[i + distance].getArea()) == up) {
				// swaps
				Shape tempShape = shapes[i];
				shapes[i] = shapes[i + distance];
				shapes[i + distance] = tempShape;
			}
		}
	}

	// This is just a convenience method so I wouldn't have to create block if/else statements but rather keep them simple
	private Shape[] copyOfRange(final Shape[] original, final int from, final int to) {
		return Arrays.copyOfRange(original, from, to);
	}

	// This method combines the first array argument with the second array argument
	private Shape[] combine(final Shape[] first, final Shape[] second) {
		Shape[] returnShapeArray = new Shape[first.length + second.length];
		for (int i = 0; i < first.length; i++) {
			returnShapeArray[i] = first[i];
		}
		for (int i = 0; i < second.length; i++) {
			returnShapeArray[i + first.length] = second[i];
		}
		return returnShapeArray;
	}

	public static void main(String[] args) {
		new SortShapesRunner();
	}

}
