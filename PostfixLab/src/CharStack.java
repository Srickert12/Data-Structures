import java.lang.IllegalStateException;

public class CharStack {
	private char[] A;
	private int n = 0;
	
	/**
	 * Constructor
	 * @param max_size     Maximum size of the queue   
	 */
	public CharStack(int maxSize) {
		A = new char[maxSize];
	}
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	public void push(char value) {
		if(n == A.length) throw new IllegalStateException();
		A[n++] = value;
	}
	
	/**
	 * Pop a value from the stack: return the value, remove from stack.
	 * @return Top values of stacke
	 * @exception IllegalStateException   Thrown if you pop from an empty stack.
	 */
	public char pop() {
		if(n == 0) throw new IllegalStateException();
		return A[--n];
	}
	
	/**
	 * Return the top value on the stack without removing it.
	 * @return Topf value of stacke.
	 * @exception IllegalStateException()  Thrown if if applied to an empty stack.
	 */
	public char peek() {
		if(n == 0) throw new IllegalStateException();
		return A[n-1];
	}
	
	/**
	 * Determine if the stack is empty.
	 * @return True if empty.
	 */
	public boolean isEmpty() {
		if(n == 0){
			return true;
			}
		return false;
	}
	
	/**
	 * Determine if the stack is at capacity.
	 * @return True if at capacity.
	 */
	public boolean isFull() {
		if(n == A.length) {
			return true;
		}
		return false;
	}
	
	/**
	 * Number of elements in the stack.
	 * @return Number of elements in the stack.
	 */
	public int size() {
		return n;
	}
}
