
public class Main {
	public static void main(String[] args) {
		// tests the isEmpty method by starting with an empty heap, calling it
		// then adding an element and calling it again
		BoundedTernaryMinHeap heap = new BoundedTernaryMinHeap();
		System.out.println(heap.isEmpty());
		heap.addElement(10);
		System.out.println(heap.isEmpty());
		
		
		// adds specific elements to the heap
		heap.addElement(5);
		heap.addElement(2);
		heap.addElement(3);
		heap.addElement(15);
		// if this works correctly, the min of the heap (the first printed item)
		// should be 2, no longer 10 from before
		heap.print();
		
		
		// should remove the min (2) and after moving 15 to the top, move it down
		// until it is smaller than its children (in this case it should not have
		// any children)
		System.out.println();
		heap.removeMin();
		heap.print();
		System.out.println();
		
		
		// gets the elements smaller than 6, should be 3 and 5
		int[] arr = heap.getSmallerThanK(6);
		for (int i = 0; i < arr.length; i ++) {
			System.out.print(arr[i] + " ");
		}
	}
}
