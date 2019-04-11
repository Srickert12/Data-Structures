package data_structures;

import java.util.ArrayList;
import java.util.Collections;

public class MinHeap<E, P extends Comparable<P>> implements MinPriorityQueue<E, P> {
	private ArrayList<Pair<E, P>> heap;
	private int n;
	private int numOps;

	public MinHeap() {
		heap = new ArrayList<Pair<E, P>>(Collections.nCopies(20, null));
		n = 0;
		numOps = 0;
	}

	private int parent(int pos) {
		return (pos - 1) / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos + 1);
	}

	private int rightChild(int pos) {
		return (2 * pos + 2);
	}

	@Override
	public void push(E e, P v) {
		if (n + 1 > heap.size())
			resize();
		Pair<E, P> a = new Pair<E, P>(e, v);
		heap.set(n, a);
		numOps++;
		n++;
		bubbleUp(n - 1);
	}

	private void bubbleUp(int i) {
		int p = parent(i);
		while (i > 0 && (heap.get(i).second.compareTo(heap.get(p).second) < 0)) {
			numOps++;
			Collections.swap(heap, i, p);
			numOps++;
			i = p;
			p = parent(i);
		}
	}

	private void resize() {
		ArrayList<Pair<E, P>> a = new ArrayList<Pair<E, P>>(Collections.nCopies(heap.size() * 2, null));
		for (int i = 0; i < heap.size(); i++) {
			a.set(i, heap.get(i));
			numOps++;
		}
		heap = a;
	}

	@Override
	public E pop() {
		if (n == 0) {
			throw new IndexOutOfBoundsException();
		}
		E e = heap.get(0).first;
		numOps++;
		heap.set(0, heap.get(--n));
		numOps++;
		trickleDown(0);
		return e;
	}

	private void trickleDown(int i) {
		do {
			int j = -1;
			int r = rightChild(i);
			if (r < n && (heap.get(r).second.compareTo(heap.get(i).second) < 0)) {
				numOps++;
				int l = leftChild(i);
				if (heap.get(l).second.compareTo(heap.get(r).second) < 0) {
					numOps++;
					j = l;
				} else {
					j = r;
				}
			} else {
				int l = leftChild(i);
				if (l < n && (heap.get(l).second.compareTo(heap.get(i).second) < 0)) {
					numOps++;
					j = l;
				}
			}
			if (j >= 0)
				Collections.swap(heap, i, j);
			numOps++;
			i = j;
		} while (i >= 0);
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if (n == 0) {
			return null;
		}
		numOps++;
		return heap.get(0).first;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean empty() {
		if (n != 0) {
			return false;
		}
		return true;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		heap = new ArrayList<Pair<E, P>>(Collections.nCopies(heap.size(), null));
		numOps++;
		n = 0;
	}

	@Override
	public void resetOps() {
		// TODO Auto-generated method stub
		numOps = 0;
	}

	@Override
	public int numOps() {
		// TODO Auto-generated method stub
		return numOps;
	}

}
