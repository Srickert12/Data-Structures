import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import data_structures.TreeNode;

public class TreeTraversal {

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a
	 * binary search tree, return an ArrayList<Integer> holding the keys in
	 * order from a post-order traversal. RESTRICTION: Use recursion for this
	 * implementation.
	 * 
	 * @param r
	 * @return Array list
	 */
	public static ArrayList<Integer> postOrder(TreeNode<Integer, Integer> r) {
		return postOrder(r, new ArrayList<Integer>());
	}

	private static ArrayList<Integer> postOrder(TreeNode<Integer, Integer> r, ArrayList<Integer> a) {
		if (r == null)
			return null;
		else {
			postOrder(r.left, a);
			postOrder(r.right, a);
			a.add(r.key);
		}
		return a;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a
	 * binary tree, return an ArrayList<Integer> holding the keys in order from
	 * a BFS traversal. RESTRICTION: Do NOT use recursion for this
	 * implementation -- use a Queue. (Or use your Linked List class as a
	 * queue.)
	 * 
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> BFS(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		Queue<TreeNode<Integer, Integer>> q = new LinkedList<TreeNode<Integer, Integer>>();
		if (root != null)
			q.add(root);
		while (!q.isEmpty()) {
			TreeNode<Integer, Integer> u = q.remove();
			a.add(u.key);
			if (u.left != null) {
				q.add(u.left);
			}
			if (u.right != null) {
				q.add(u.right);
			}
		}
		return a;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a
	 * binary tree, return an ArrayList<Integer> holding the keys in order from
	 * a pre-order traversal. RESTRICTION: Do NOT use recursion for this
	 * implementation -- use a Stack. (Or use your Linked List class as a
	 * stack.)
	 * 
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> preOrder(TreeNode<Integer, Integer> root) {
		if (root == null) {
			return null;
		}
		Stack<TreeNode<Integer, Integer>> s = new Stack<TreeNode<Integer, Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		s.push(root);
		while (!s.isEmpty()) {
			TreeNode<Integer, Integer> r = s.peek();
			a.add(r.key);
			s.pop();
			if (r.right != null) {
				s.push(r.right);
			}
			if (r.left != null) {
				s.push(r.left);
			}

		}
		return a;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a
	 * binary tree, return an ArrayList<Integer> holding the keys in order from
	 * an in-order traversal. RESTRICTION: Do NOT use recursion for this
	 * implementation -- use a Stack. (Or use your Linked List class as a
	 * stack.)
	 * 
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> inOrder(TreeNode<Integer, Integer> root) {
		if (root == null) {
			return null;
		}
		Stack<TreeNode<Integer, Integer>> s = new Stack<TreeNode<Integer, Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		TreeNode<Integer, Integer> cur = root;

		while (cur != null) {
			s.push(cur);
			cur = cur.left;
		}

		while (s.size() > 0) {
			cur = s.pop();
			a.add(cur.key);
			if (cur.right != null) {
				cur = cur.right;
				while (cur != null) {
					s.push(cur);
					cur = cur.left;
				}
			}
		}
		return a;
	}

}
