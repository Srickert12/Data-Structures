package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * @author karroje
 *
 */
public class StringHash<value_type> extends Dictionary<String, Integer> {
	int a;
	int b;
	int m;

	ArrayList<Pair<String, Integer>> table;

	/**
	 * Hashing function
	 * 
	 * @param key
	 * @return hash value
	 */
	private int hash(String s) {
		int total = 0;
		int count = 0;
		for(int i = s.length()-1; i >= 0; i--) {
			total = (total*256 + a*s.charAt(count)) % m;
			count++;
		}
		total += b;
		total = total % m;
		return total;
	}

	/**
	 * Default constructor
	 */
	public StringHash() {
		this(7, 1, 25014);
	}

	/**
	 * Constructor -- hash values specified.
	 */
	public StringHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<String, Integer>>(Collections.nCopies(m, null));
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
			Pair<String, Integer> p = table.get(i);
			if (p != null)
				System.out.println("k, h(k), v = " + p.first + " " + i + " " + p.second);
		}
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
	public void insert(String key, Integer value) {
		if (value == null)
			throw new IllegalArgumentException("Null values not allowed");
		if (n == m)
			throw new ArrayIndexOutOfBoundsException("Out of space");
		if (find(key) != null)
			throw new IndexOutOfBoundsException();

		// TODO
		int i = hash(key);
		Pair<String, Integer> entry = new Pair<String, Integer>(key, value);

		while (table.get(i) != null) {
			i = (i + 1) % table.size();
			numOps++;
		}
		table.add(i, entry);
		numOps++;
		n++;
	}

	@Override
	public void remove(String key) {
		int i = hash(key);
		while (!table.get(i).first.equals(key)) {
			i = (i + 1) % table.size();
			numOps++;
		}
		table.remove(i);
		numOps++;
		n--;
	}

	@Override
	public Integer find(String key) {
		int i = hash(key);
		while (table.get(i) != null) {
			if (table.get(i).first.equals(key)) {
				numOps++;
				return table.get(i).second;
			}
			i = (i + 1) % table.size();
			numOps++;
		}
		return null;
	}

	public void incrementData(String key, int amount) {
		int i = hash(key);
		while(table.get(i) != null) {
			if(table.get(i).first.equals(key)) {
				table.get(i).second = table.get(i).second + amount;
			}
			i = (i+1) % table.size();
		}
	}
	
	@Override
	public void clear() {
		table.clear();
	}
	
	public void resize() {
		ArrayList<Pair<String, Integer>> table2 = new ArrayList<Pair<String,Integer>>(Collections.nCopies(m*2, null));
		
		}
}
