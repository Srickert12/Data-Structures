package unit_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import data_structures.MinHeap;
import data_structures.MinPriorityQueue;

import org.junit.Test;

public class MinHeapTester {

	// Tests for size of heap
	@Test
	public void test01() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		assertEquals(H.size(), 0);
	}

	// Tests push for multiple inserts
	@Test
	public void test02() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.push("A", 3);
		H.push("B", 2);
		H.push("C", 1);
		H.push("D", 0);
		assertEquals("D", H.peek());
	}

	// Test insert method for one insert
	@Test
	public void test03() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.push("A", 3);
		assertEquals("A", H.peek());
	}

	// Tests pop with multiple inserts
	@Test
	public void test04() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.push("A", 3);
		H.push("B", 2);
		H.push("C", 1);
		H.push("D", 0);
		H.pop();
		assertEquals("C", H.peek());
	}

	// Tests pop for one insert
	@Test
	public void test05() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.push("A", 3);
		H.pop();
		assertEquals(0, H.size());
	}

	// Tests for exception when pop is called on empty heap
	@Test(expected = IndexOutOfBoundsException.class)
	public void test06() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.pop();
	}

	// Tests clear method
	@Test
	public void test07() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.push("A", 3);
		H.push("B", 2);
		H.push("C", 1);
		H.push("D", 0);
		H.clear();
		assertEquals(null, H.peek());
	}

	// Tests empty method for true
	@Test
	public void test08() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		assertEquals(true, H.empty());
	}

	// Tests empty method for false
	@Test
	public void test09() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.push("A", 0);
		assertEquals(false, H.empty());
	}

	// Tests for multiple pushes and pops
	@Test
	public void test10() {
		MinPriorityQueue<String, Integer> H = new MinHeap<>();
		H.push("A", 14);
		H.push("B", 8);
		H.push("C", 10);
		H.pop();
		H.push("D", 0);
		H.push("E", 5);
		H.pop();
		assertEquals("E", H.peek());
	}
}
