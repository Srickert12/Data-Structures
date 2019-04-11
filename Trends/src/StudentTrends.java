import java.util.*;
import java.util.Map.Entry;

import data_structures.Pair;
import data_structures.StringHash;

/**
 *
 * @author // TODO: Sam Rickert
 * I ended up using the java library hashmap because I couldn't
 * get mine to work so most of the files in the project directory are
 * unused besides the pair class
 */
public class StudentTrends implements Trends {
	private HashMap<String, Integer> words;
	private ArrayList<Pair<String, Integer>> toSort;
	private boolean needsSort = false;

	public StudentTrends() {
		words = new HashMap<String, Integer>();
		toSort = new ArrayList<Pair<String, Integer>>();
	}

	@Override
	public void increaseCount(String s, int amount) {
		// If string s is not in the hash map, add it to the hashmap
		if (words.get(s) == null) {
			words.put(s, amount);
		}
		// If string s is in the hashmap, replace it with itself with new amount
		// added to value
		else {
			words.put(s, words.get(s) + amount);
		}
		// Since a new entry is added to the hashmap, it needs to be re-sorted
		needsSort = true;
	}

	@Override
	public int getCount(String s) {
		// if the string is not in the hashmap, return 0, else return the count
		// of string s
		if (words.get(s) == null) {
			return 0;
		}
		return words.get(s);
	}

	@Override
	public String getNthPopular(int n) {
		// sorts the array list by popularity if it needs to be sorted and
		// returns the nth popular item
		if (needsSort == true) {
			sortMap(toSort);
		}
		return toSort.get(n).first;
	}

	@Override
	public int numEntries() {
		// returns the number of entries in the hash map
		return words.size();
	}

	private void sortMap(ArrayList<Pair<String, Integer>> sorted) {
		// Fill arraylist with all hashmap entries
		sorted.clear();
		for (Entry<String, Integer> entry : words.entrySet()) {
			String s = entry.getKey();
			Integer val = entry.getValue();
			sorted.add(new Pair<String, Integer>(s, val));
		}
		// Sorts the array list with insertion sort in descending order
		int i;
		int j;
		Pair<String, Integer> temp;
		for (i = 1; i < sorted.size(); i++) {
			j = i;
			while (j > 0 && sorted.get(j).second >= sorted.get(j - 1).second) {
				// If two values are equal and in alphabetical order, do nothing
				if (sorted.get(j).second == sorted.get(j - 1).second
						&& sorted.get(j).first.compareTo(sorted.get(j - 1).first) > 0) {
					j--;
				}
				// else, swap the two pairs
				else {
					temp = sorted.get(j - 1);
					sorted.set(j - 1, sorted.get(j));
					sorted.set(j, temp);
					j--;
				}
			}
		}
		// The array list is sorted so the next time get nth
		// popular is called, we do not need to re-sort.
		needsSort = false;
	}
}
