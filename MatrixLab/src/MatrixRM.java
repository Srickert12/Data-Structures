/**
 *
 */

/**
 * @author Sam Rickert
 *
 */
public class MatrixRM extends Matrix {
	int[] A;

	/**
	 * Constructor. Should initialize all values to 0.
	 *
	 * @param num_rows
	 * @param num_cols
	 */
	public MatrixRM(int num_rows, int num_cols) {
		super(num_rows, num_cols);
		A = new int[num_rows * num_cols];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Matrix#get(int, int)
	 */
	@Override
	public int get(int i, int j) {
		return A[j + i * numCols()];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Matrix#set(int, int, int)
	 */
	@Override
	public void set(int i, int j, int val) {
		A[j + i * numCols()] = val;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Matrix#find(int)
	 */
	public int[] find(int val) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == val) {
				int[] B = { numRows(), numCols() };
				return B;
			}
		}
		// NOTE: DO this WITHOUT calling this.get() -- access A directly.
		return null;
	}

}
