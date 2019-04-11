
import data_structures.IntBST;

public class TreeTraversalTester {

	public static void main(String[] args) {
		// Creates BST
		IntBST tree = new IntBST(8);
		// Prints structure of tree
		System.out.println("Tree Structure: ");
		System.out.println(" ");
		System.out.println(" ");
		tree.print_structure();
		System.out.println(" ");
		
		// Test of Breadth first search
		System.out.println("Tree traversal (BSF):");
		System.out.println("Expected: [8, 4, 12, 2, 6, 10, 14, 0] ");
		System.out.println("Result: " + TreeTraversal.BFS(tree.getRoot()).toString());
		System.out.println(" ");
		
		// Test of In Order Depth first search
		System.out.println("Tree traversal (In-Order):");
		System.out.println("Expected: [0, 2, 4, 6, 8, 10, 12, 14]");
		System.out.println("Result: " + TreeTraversal.inOrder(tree.getRoot()).toString());
		System.out.println(" ");
		
		// Test of Post order depth first search
		System.out.println("Tree traversal (Post-Order):");
		System.out.println("Expected: [0, 2, 6, 4, 10, 14, 12, 8]");
		System.out.println("Result: " + TreeTraversal.postOrder(tree.getRoot()).toString());
		System.out.println(" ");
		
		// Test of Pre order depth first search
		System.out.println("Tree traversal (Pre-Order):");
		System.out.println("Expected: [8, 4, 2, 0, 6, 12, 10, 14]");
		System.out.println("Result: " + TreeTraversal.preOrder(tree.getRoot()).toString());

	}

}
