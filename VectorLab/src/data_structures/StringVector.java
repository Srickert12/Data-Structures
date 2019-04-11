package data_structures;

import java.lang.IndexOutOfBoundsException;

/**
 * StringArray: An implementation of a dynamic-sized string array.
 * 
 * @author TODO: Sam Rickert
 *
 */

public class StringVector extends Sequence {

	/**
	 * A: Contains the actual array.
	 */
	private String[] A;

	/**
	 * n: Size of array
	 */

	/**
	 * Basic constructor: Creates an "empty" list.
	 */
	public StringVector() {
		A = new String[1];
		n = 0;
	}

	/**
	 * Get the string i.
	 * 
	 * @param i
	 *            Number of accessed element (from 0).
	 * @return string
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public String get(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		numOps++;
		return A[i];
	}

	/**
	 * Set the string at element i to value.
	 * 
	 * @param i
	 *            Number of accessed element (from 0).
	 * @param value
	 *            String value being put in the array
	 * @return string
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public String set(int i, String value) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		String y = A[i];
		A[i] = value;
		numOps+= 2;
		return y;
	}

	/**
	 * Insert a new value at index i (bumping elements i - size() up one).
	 * 
	 * @param i
	 *            Number of inserted element
	 * @param value
	 *            String valueb eing inserted
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public void add(int i, String value) {
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();
		 resize(); // This will make sure A is large enough to hold the new
					// element
		for (int j = n; j > i; j--) {
			A[j] = A[j - 1];
			numOps++;
		}
		A[i] = value;
		numOps++;
		n++;
	}

	/**
	 * Remove element i from the array (renumbering everything past it)
	 * 
	 * @param i
	 *            element being removed
	 * @return value that was removed
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public String remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		String value = A[i];
		for (int j = i; j < n - 1; j++) {
			A[j] = A[j + 1];
			numOps++;
		}
		n--;
		return value;
	}

	/**
	 * If the array is full (that is: A.length == n), resize it so it can hold
	 * one more element.
	 */
	private void resize() {
		if (A.length == n) {
			String[] B = new String[n*2];
			for (int i = 0; i < A.length; i++) {
				B[i] = A[i];
				numOps++;
			}
			A = B;
		}
	}

	/**
	 * Remove all elements.
	 */
	public void clear() {
		A = new String[1];
		n = 0;
	}
}