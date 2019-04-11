/**
 *
 */

/**
 * @author Sam Rickert
 *
 */
public class Array2D extends Matrix {
	public int[][] A;

	/**
	 * Constructor. Should initialize all values to 0.
	 *
	 * @param num_rows
	 * @param num_cols
	 */
	public Array2D(int num_rows, int num_cols) {
		super(num_rows, num_cols);
		A = new int[num_rows][];
		for (int i = 0; i < num_rows; i++)
			A[i] = new int[num_cols];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Matrix#get(int, int)
	 */
	@Override
	public int get(int i, int j) {
		return A[i][j];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Matrix#set(int, int, int)
	 */
	@Override
	public void set(int i, int j, int val) {
		A[i][j] = val;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Matrix#find(int)
	 */
	public int[] find(int val) {
		// NOTE: DO this WITHOUT calling this.get() -- access A directly
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if (A[i][j] == val) {
					int[] a = {i, j};
					return a;
				}
			}
		}
		return null;
	}
}
