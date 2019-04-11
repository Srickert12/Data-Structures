
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam Rickert
 *
 */
public class ListGraph extends Graph {
	private List<Pair<Integer, Double>>[] g;

	public ListGraph(int n) {
		// TODO
		super(n);
		g = (List<Pair<Integer, Double>>[]) new List[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<Pair<Integer, Double>>();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		// TODO
		for (int a = 0; a < g[i].size(); a++) {
			if (g[i].get(a).first == j)
				return g[i].get(a).second;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see Graph#addEdge(int, int)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		// TODO
		g[i].add(new Pair<Integer, Double>(j, weight));
		g[j].add(new Pair<Integer, Double>(i, weight));
	}

	/*
	 * (non-javadoc)
	 *
	 * @see Graph#AdjacentNodes
	 */
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		// TODO
		return (ArrayList<Pair<Integer, Double>>) g[i];
	}

	/*
	 * (non-javadoc)
	 *
	 * @see Graph#degree
	 */
	int degree(int i) {
		// TODO
		return g[i].size();
	}

}
