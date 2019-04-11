package data_structures;

import java.util.ArrayList;
import java.util.Collections;

import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * @author Sam Rickert
 *
 */
public class IntHash<value_type> extends Dictionary<Integer, value_type> {
	int a;
	int b;
	int m;

	ArrayList<Pair<Integer, value_type>> table;
	Pair<Integer, value_type> del = new Pair<Integer, value_type>(0, null);

	/**
	 * Hashing function
	 *
	 * @param key
	 * @return hash value
	 */
	private int hash(Integer key) {
		return (((int) key * a) + b) % m;
	}

	/**
	 * Default constructor
	 */
	public IntHash() {
		this(7, 1, 25014);
	}

	/**
	 * Constructor -- hash values specified.
	 */
	public IntHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<Integer, value_type>>(Collections.nCopies(m, null));
	}

	/**
	 * Insert a value/key pair into the dictionary. Do not allow duplicate or
	 * null values.
	 *
	 * @param key
	 *            key to be inserted
	 * @param value
	 *            value to be inserted
	 * @exception Throw
	 *                IndexOutOfBoundsException if key already present.
	 * @exception Throw
	 *                IllegalArgumentException if value is null.
	 * @exception Throw
	 *                IllegalArgumentException if key < 0. (Makes life easier.
	 */
	@Override
	public void insert(Integer key, value_type value) {
		if (value == null)
			throw new IllegalArgumentException("Null values not allowed");
		if (n == m)
			throw new ArrayIndexOutOfBoundsException("Out of space");
		if (key < 0)
			throw new IllegalArgumentException("Negative keys not allowed");
		if (find(key) != null)
			throw new IndexOutOfBoundsException();

		// TODO
		int i = hash(key);
		Pair<Integer, value_type> entry = new Pair<Integer, value_type>(key, value);

		while (table.get(i) != null && table.get(i) != del) {
			i = (i + 1) % table.size();
			numOps++;
		}
		table.set(i, entry);
		numOps++;
		n++;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(Integer key) {
		int i = hash(key);
		while (table.get(i) != null) {
			if (key.equals(table.get(i).first) && table.get(i) != del) {
				table.set(i, del);
				n--;
			}
			i = (i + 1) % table.size();
			numOps++;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		// TODO: Write method
		table = new ArrayList<Pair<Integer, value_type>>(Collections.nCopies(m, null));
		numOps++;
		n = 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public value_type find(Integer key) {
		// TODO: Write method
		int i = hash(key);
		while (table.get(i) != null) {
			if (table.get(i).first.equals(key) && table.get(i) != del) {
				numOps++;
				return table.get(i).second;
			}
			i = (i + 1) % table.size();
			numOps++;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#check_structure() This is not useful for
	 * this class -- we will just always pass it.
	 */
	@Override
	public boolean check_structure() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		for (int i = 0; i < m; i++) {
			Pair<Integer, value_type> p = table.get(i);
			if (p != null && p.first >= 0)
				System.out.println("k, h(k), v = " + p.first + " " + i + " " + p.second);
		}
	}

	public void resize() {
		ArrayList<Pair<Integer, value_type>> table2 = table;
		for (int i = 0; i < table2.size(); i++) {
			if (table2.get(i) != null && table2.get(i) != null) {
				int j = hash(table2.get(i).first);
				while (table.get(i) != null)
					i = (1 + 1) % table.size();
				table.set(j, table2.get(i));
			}

		}
	}
}
