import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class GraphAlgs {

	/**
	 * Read a graph from a file where nodes are labeled by integers from 0 to
	 * n-1.
	 * 
	 * @param file
	 * @param list_graph
	 * @return
	 */
	static public Graph readGraph(String file, boolean list_graph) {
		Graph G;
		try {
			Scanner S = new Scanner(new File(file));
			int n = S.nextInt();
			G = list_graph ? new ListGraph(n) : new MatrixGraph(n);

			while (S.hasNext()) {
				int u = S.nextInt();
				int v = S.nextInt();
				Double w = S.nextDouble();
				G.setWeight(u, v, w);
			}
			S.close();
			return G;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return null;
	}

	/**
	 * Read a graph from a file where nodes are labeled by strings.
	 * 
	 * @param file
	 * @param list_graph
	 * @return
	 */
	static public Graph readNamedGraph(String file, boolean list_graph) {
		Graph G;
		try {
			Scanner S = new Scanner(new File(file));
			int n = S.nextInt();
			G = list_graph ? new ListGraph(n) : new MatrixGraph(n);
			for (int i = 0; i < n; i++)
				G.addName(i, S.next());

			while (S.hasNext()) {
				String u = S.next();
				String v = S.next();
				Double w = S.nextDouble();

				G.setWeight(u, v, w);
			}
			S.close();
			return G;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return null;
	}

	/**
	 * Write a graph to a file using node numbers.
	 * 
	 * @param G
	 * @param file
	 */
	static public void writeGraph(Graph G, String file) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println(G.numNodes());
			for (int i = 0; i < G.numNodes(); i++) {
				ArrayList<Pair<Integer, Double>> P = G.adjacentNodes(i);
				for (Pair<Integer, Double> j : P) {
					if (i < j.first) {
						out.println(i + " " + j.first + " " + j.second);
					}
				}

			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write a graph to a file using strings.
	 * 
	 * @param G
	 * @param file
	 */
	static public void writeNamedGraph(Graph G, String file) {
		try {
			PrintWriter out = new PrintWriter(file);
			out.println(G.numNodes());
			for (int i = 0; i < G.numNodes(); i++)
				out.println(G.getName(i));
			for (int i = 0; i < G.numNodes(); i++) {
				ArrayList<Pair<Integer, Double>> P = G.adjacentNodes(i);
				for (Pair<Integer, Double> j : P) {
					if (i < j.first) {
						out.println(G.getName(i) + " " + G.getName(j.first) + " " + j.second);
					}
				}

			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static public Graph randomGraph(int n, double p, boolean list_graph) {
		Random rng = new Random();
		Graph G = GraphFactory.make_graph(n, list_graph);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (i != j && rng.nextDouble() < p)
					G.setWeight(i, j, ((double) (rng.nextInt(19) + 1)));
		}
		return G;
	}

	/**
	 * Given a directed graph, return an array list of node numbers that have
	 * been topologically sorted. (That is, if there is an edge from node a to
	 * node b, b must come after a in the graph.) You may assume that there are
	 * no cycles in G (so it is always possible to sort.) EXTRA CREDIT: Do not
	 * assume there are no cycles, and return an empty array if that is the
	 * case.
	 * 
	 * @param G
	 * @return
	 */
	static public ArrayList<Integer> topologicalSort(Graph G) {
		// TODO
		// Creates a deep copy of the graph
		ListGraph Copy = (ListGraph) G.clone();
		// ArrayList to return
		ArrayList<Integer> f = new ArrayList<Integer>();
		// Queue that stores nodes with indegree = 0
		Queue<Integer> S = new LinkedList<Integer>();
		// Array that stores indegrees of all nodes
		int[] indegree = new int[Copy.num_nodes];
		//counts cycles
		int count = 0;
		for(int i = 0; i < indegree.length; i++) {
			indegree[i] = Copy.inDegree(i);
		}
		// If a node in graph has indegree = 0, add it to queue
		for(int i = 0; i < Copy.num_nodes; i++) {
			if(Copy.inDegree(i) == 0) {
				S.add(i);
			}
		}
		// While queue is not empty
		while(!S.isEmpty()) {
			// remove n from stack
			int n = S.poll();
			// add n to the final list
			f.add(n);
			// decrement all indegrees of nodes adjacent to n
			// and add to queue if an indegree is now 0
			for(Pair<Integer, Double> i: Copy.adjacentNodes(n)) {
				if(--indegree[i.first] == 0) {
					S.add(i.first);
				}
			}
			count++;
		}
		if(count != Copy.num_nodes) {
			f.clear();
		}
		// return the final list
		return f;
	}
}
