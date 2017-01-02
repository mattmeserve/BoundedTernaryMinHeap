import java.util.ArrayList;
/**
 * Bounded Ternary Min Heap
 * @author mjmeserve
 *
 */
public class BoundedTernaryMinHeap {
	private int[] heap = new int[1000];
	private int size = 0;
	public BoundedTernaryMinHeap() {
	}
	/**
	 * Checks size - if it's 0, the heap is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * adds i to the end of the heap, then calls a helper to move it into place
	 * @param i - the value to be added
	 */
	public void addElement(int i) {
		heap[size] = i;
		checkParents(size);
		size ++;
	}
	
	/**
	 * recursive method - recurses to move an element up the tree
	 * @param val = the position in the heap array of the element that needs
	 * to move up (if necessary)
	 */
	private void checkParents(int val) {
		// if the element is the root, stop
		if (val == 0) {
			return;
		} else {
			// otherwise, check if its smaller than its parent
			if (heap[val] < heap[(val - 1) / 3]) {
				// if it is, swap them, then recurse with the new element
				int temp = heap[val];
				heap[val] = heap[(val - 1) / 3];
				heap[(val - 1) / 3] = temp;
				checkParents((val - 1) / 3);
			} else {
				// if it is greater than its parent, it is in the right
				// position, and the method can stop
				return;
			}
		}
	}
	
	/**
	 * Gets the root of the heap
	 * @return the first value of the heap array
	 */
	public int getMin() {
		return heap[0];
	}
	
	/**
	 * removes the min, sets the last element of the array to heap[0],
	 * then calls a helper method to move heap[0] down correctly
	 */
	public void removeMin() {
		// if the size is 1, just set heap[0] to 0 and return
		if (size == 1) {
			heap[0] = 0;
			size --;
			return;
		}
		heap[0] = heap[size - 1];
		moveIntoPlace(0);
		size --;
	}
	
	/**
	 * Checks the number of children of heap[position] and only recurses if
	 * it has 3 children
	 * @param position - the current position of the element that needs
	 * to be adjusted
	 */
	private void moveIntoPlace(int position) {
		int min;
		// if heap[position] has 0 children
		if (size < (position * 3) + 1) {
			return;
			// if it has 1 child
		} else if (size < (position * 3) + 2) {
			if (heap[position] < heap[(position * 3) + 1]) {
				swap(position, (position * 3) + 1);
			}
			return;
			// if it has 2 children
		} else if (size < (position * 3) + 3) {
			min = findMin(position, (position * 3) + 1, 
					(position * 3) + 2);
			if (min != position) {
				swap(position, min);
			}
			return;
			// if it has 3 children
		} else {
			min = findMin(position, (position * 3) + 1, 
				(position * 3) + 2, (position * 3) + 3);
			if (min == position) {
				return;
			} else {
				swap(position, min);
				moveIntoPlace(min);
			}
		}
	}
	
	/**
	 * Prints the heap
	 */
	public void print() {
		for(int i = 0; i < size; i ++) {
			System.out.print(heap[i] + " ");
		}
	}
	
	/**
	 * Checks which heap position is the smallest
	 * @param a, b, c 3 positions to find the min between
	 * @return the min position
	 */
	private int findMin(int a, int b, int c) {
		int min = a;
		if (heap[b] < heap[min]) {
			min = b;
		}
		if (heap[c] < heap[min]) {
			min = c;
		}
		return min;
	}
	/**
	 * Same as the above method, but with 4 params
	 * @param a, b, c, d 4 positions to find the min of
	 * @return the min position
	 */
	private int findMin(int a, int b, int c, int d) {
		int min = a;
		if (heap[b] < heap[min]) {
			min = b;
		}
		if (heap[c] < heap[min]) {
			min = c;
		}
		if (heap[d] < heap[min]) {
			min = d;
		}
		return min;
	}
	
	/**
	 * Swaps 2 heap elements
	 * @param posA, posB 2 positions in the heap array
	 */
	private void swap(int posA, int posB) {
		int temp = heap[posA];
		heap[posA] = heap[posB];
		heap[posB] = temp;
	}
	
	/**
	 * Calls a recursive helper method and copies values into an arraylist
	 * individually, then copies that into an array
	 * @param k the number to compare the elements to
	 * @return the array of ints that are smaller than k
	 */
	public int[] getSmallerThanK(int k) {
		ArrayList<Integer> list = new ArrayList<>();
		smallerThanK(k, 0, list);
		int[] ret = new int[list.size()];
		for (int i = 0; i < ret.length; i ++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	/**
	 * Builds an arraylist of values smaller than k by going down the array
	 * recursively until it reaches a number greater than k
	 * @param k the number to compare the elements to
	 * @param pos the current position in the heap
	 * @param list the running list of elements larger than k
	 */
	private void smallerThanK(int k, int pos, ArrayList<Integer> list) {
		// base case = if the heap element is greater than k
		if (heap[pos] >= k) {
			return;
		} else {
			// otherwise, adds the heap element to the arraylist and recurses
			// accordingly to each of its children
			list.add(heap[pos]);
			if (size > (pos * 3) + 1) {
				smallerThanK(k, (pos * 3) + 1, list);
				if (size > (pos * 3) + 2) {
					smallerThanK(k, (pos * 3) + 2, list);
					if (size > (pos * 3) + 3) {
						smallerThanK(k, (pos * 3) + 3, list);
					}
				}
			}
		}
	}
}
