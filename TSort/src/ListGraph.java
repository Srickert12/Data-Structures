
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
		num_nodes = n;
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

	public int inDegree(int n) {
		int count = 0;
			for(int j = 0; j < num_nodes; j++) {
				for(int k = 0; k < adjacentNodes(j).size(); k++) {
					if(adjacentNodes(j).get(k).first == n)
						count++;
				}
			}

		return count;
	}

	@Override
	public Graph clone() {
		ListGraph a = new ListGraph(num_nodes);
		for(int i = 0; i <num_nodes; i++) {
			for(int j = 0; j < g[i].size(); j++) {
				a.g[i].add(j, g[i].get(j));
			}
		}
		return a;
	}
}
