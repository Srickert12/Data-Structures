/**
 *
 */
package data_structures;

/**
 * @author Sam Rickert
 *
 */

/**
 *
 *
 *
 * @param <value_type>
 *            The type of object to be stored in the list.
 */
class ListNode<value_type> {
	public value_type value;
	public ListNode<value_type> next;

	public ListNode(value_type v) {
		value = v;
		next = null;
	}

	public ListNode(value_type v, ListNode<value_type> n) {
		value = v;
		next = n;
	}
}

/*
 * We will implement this as a single linked list.
 */
public class LinkedList<value_type> extends Sequence<value_type> {

	/**
	 * head will be the first node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> head;

	/**
	 * List constructor: must call the superclass constructor.
	 */
	public LinkedList() {
		super();
		// TO DO: Finish constructor.
		head = new ListNode<value_type>(null);
	}

	@Override
	public boolean in(value_type query) {
		ListNode<value_type> temp = head;
		while(temp != null) {
			if(temp.value == query) {
				return true;
			}
			temp = temp.next;
			numOps++;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#get(int)
	 */
	@Override
	public value_type get(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();

		ListNode<value_type> temp = head;
		for (int j = 0; j <= i; j++) {
			temp = temp.next;
			numOps++;
		}
		return temp.value;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#set(int, java.lang.String)
	 */
	@Override
	public value_type set(int i, value_type value) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();

		// TO DO: Finish method.
		ListNode<value_type> temp = head;
		for (int j = 0; j < i; j++) {
			temp = temp.next;
			numOps++;
		}
		value_type a = temp.value;
		numOps++;
		temp.value = value;
		numOps++;
		return a;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#add(int, java.lang.String)
	 */
	@Override
	public void add(int i, value_type value) {
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();

		ListNode<value_type> temp = head;
		ListNode<value_type> toAdd = new ListNode<value_type>(value);
		for (int j = 0; j < i; j++) {
			temp = temp.next;
			numOps++;
		}
		toAdd.next = temp.next;
		numOps++;
		temp.next = toAdd;
		numOps++;
		n++;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#remove(int)
	 */
	@Override
	public value_type remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		if (size() == 0)
			return null;

		ListNode<value_type> temp = head;
		for (int j = 0; j < i; j++) {
			temp = temp.next;
			numOps++;
		}
		value_type a = temp.next.value;
		numOps++;
		temp.next = temp.next.next;
		numOps++;
		n--;

		return a;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data_structures.Sequence#clear()
	 */
	@Override
	public void clear() {
		// TO DO: Finish method.
		head.next = null;
		n = 0;
	}
}
