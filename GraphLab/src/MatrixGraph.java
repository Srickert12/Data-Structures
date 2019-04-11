import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Sam Rickert
 *
 */
public class MatrixGraph extends Graph {
	private double[][] g;
	private int num_rows;

	public MatrixGraph(int n) {
		super(n);
		g = new double[n][n];
		num_rows = n;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		if (g[i][j] == 0) {
			return null;
		}
		return g[i][j];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Graph#setWeight(int, int, java.lang.Double)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		g[i][j] = weight;
		g[j][i] = weight;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Graph#adjacentNodes(int)
	 */
	@Override
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		ArrayList<Pair<Integer, Double>> list = new ArrayList<Pair<Integer, Double>>();
		for (int a = 0; a < num_rows; a++) {
			if (g[i][a] != 0) {
				list.add(new Pair<Integer, Double>(a, g[i][a]));
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Graph#degree(int)
	 */
	@Override
	int degree(int i) {
		// TODO
		int count = 0;
		for (int a = 0; a < num_rows; a++) {
			if (g[i][a] != 0) {
				count++;
			}
		}
		return count;
	}

}
