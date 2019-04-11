
package data_structures;

import java.lang.IndexOutOfBoundsException;

/**
 * @author Sam Rickert
 *
 */
public class BST<K extends Comparable<K>, V> extends Dictionary<K, V> {
	protected TreeNode<K, V> root;

	/**
	 *
	 */
	public BST() {
		super();
		root = null;
	}

	/**
	 * Compare two keys and increment numOps.
	 *
	 * @param k1
	 *            First key
	 * @param k2
	 *            Second key
	 * @return -1: k1 smaller; 0: elements equal; 1: k22 smaller
	 */
	private int compareKeys(K k1, K k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Get the tree's root node.
	 *
	 * @return
	 */
	public TreeNode<K, V> getRoot() {
		return root;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#insert(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {
		TreeNode<K, V> a = new TreeNode<K, V>(key, value);
		if (root == null) {
			root = a;
			n++;
			return;
		}
		TreeNode<K, V> cur = root;
		TreeNode<K, V> parent = null;
		while (cur != null) {
			parent = cur;
			int i = compareKeys(key, cur.key);
			if (i < 0)
				cur = cur.left;
			else if (i > 0)
				cur = cur.right;
			else {
				throw new IndexOutOfBoundsException();
			}
		}
		int i = compareKeys(key, parent.key);
		if (i < 0) {
			parent.left = a;
			n++;
		} else {
			parent.right = a;
			n++;
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(K key) {
		n--;
		root = remove(key, root);
	}

	// private recursive remove helper method
	private TreeNode<K, V> remove(K key, TreeNode<K, V> cur) {
		if (cur == null) {
			return null;
		}
		int i = compareKeys(key, cur.key);
		if (i < 0)
			cur.left = remove(key, cur.left);
		else if (i > 0)
			cur.right = remove(key, cur.right);
		else {
			// no children
			if (cur.left == null && cur.right == null) {
				return null;
			}
			// left child
			else if (cur.right == null) {
				return cur.left;
			}
			// right child
			else if (cur.left == null) {
				return cur.right;
			}
			// has two children
			else if(cur.left != null && cur.right != null){
				cur.key = findMin(cur.right).key;
                cur.right = remove(cur.key, cur.right);
			}
		}
		return cur;
	}

	// private method to find the minimum value of the subtree
	// used when removing a node with two children
	private TreeNode<K, V> findMin(TreeNode<K, V> temp) {
		if( temp == null )
            return null;
        else if( temp.left == null )
            return temp;
        return findMin( temp.left );
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public V find(K key) {
		TreeNode<K, V> cur = root;
		while (cur != null) {
			int i = compareKeys(key, cur.key);
			if (i < 0) {
				cur = cur.left;
			}
			if (i > 0) {
				cur = cur.right;
			} else if (i == 0) {
				return cur.value;
			}
		}
		return null;
	}

	/**
	 * Return the smallest value in the tree. (Return null if empty)
	 *
	 * @return key
	 */
	public K min() {
		TreeNode<K, V> cur = root;
		while (cur.left != null) {
			cur = cur.left;
		}
		return cur.key;
	}

	/**
	 * Return the smallest value in the tree. (Return null if empty)
	 *
	 * @return key
	 */
	public K max() {
		TreeNode<K, V> cur = root;
		while (cur.right != null) {
			cur = cur.right;
		}
		return cur.key;
	}

	/**
	 * Return the height of the tree. Definition: The *depth* of a node is
	 * number of edges from the root to that node. The *height* of a tree is
	 * equal to the depth of the node with the greatest depth of all the nodes.
	 *
	 * @return int
	 */
	public int height() {
		return height(root);
	}

	private int height(TreeNode<K, V> r) {
		if (r == null)
			return -1;
		return 1 + Math.max(height(r.left), height(r.right));
	}

	boolean isBSTHelper(TreeNode<K, V> root, K min_value, K max_value) {
		if (root == null)
			return true;

		if ((min_value != null && root.key.compareTo(min_value) <= 0)
				|| (max_value != null && root.key.compareTo(max_value) >= 0))
			return false;

		return isBSTHelper(root.left, min_value, root.key) && isBSTHelper(root.right, root.key, max_value);
	}

	/**
	 * Check that the tree is a BST.
	 *
	 * @param root
	 *            Root of tree being checked.
	 * @return
	 */
	boolean isBST(TreeNode<K, V> root) {
		return isBSTHelper(root, null, null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#check_structure()
	 */
	@Override
	public boolean check_structure() {
		return isBST(root);
	}

	void print_structure_helper(TreeNode<K, V> root, int indent) {
		for (int i = 0; i < indent; i++)
			System.out.print("\t");
		if (root == null) {
			System.out.println("LEAF");
			return;
		}
		System.out.println(root.key + ": " + root.value);
		print_structure_helper(root.left, indent + 1);
		print_structure_helper(root.right, indent + 1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		print_structure_helper(root, 0);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		n = 0;
		root = null;
	}

}
