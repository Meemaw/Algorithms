import java.util.Arrays;

public class PriorityQueue {

    // Implementation of Min or Max oriented PriorityQueue. By default data structure is imlemented as Min PriorityQueue
    private int[] elements;
    private int numElements;
    private int maxElements;
    private boolean isMin = true;

    // If boolean false is provided PriorityHeap is max oriented
    public PriorityQueue(int[] A, boolean isMin) {
        this(A);
        this.isMin = isMin;
    }

    public PriorityQueue(boolean isMin) {
        this();
        this.isMin = isMin;
    }

    public PriorityQueue(int[] A) {
        this.maxElements = A.length;
        this.elements = A;
        this.numElements = A.length;
        buildPriorityQueue(A);
    }

    public PriorityQueue() {
        this.maxElements = 10;
        this.elements = new int[this.maxElements];
        this.numElements = 0;
    }

    // If array of integers is provided we can build heap in O(n)
    private void buildPriorityQueue(int[] A) {
        for (int i = A.length / 2; i >= 1; i--) {
            heapify(A, i-1);
        }
    }

    public int getMaximum() {
        return getMaximum(this.elements);
    }

    private int getMaximum(int[] A) {
        if (this.numElements == 0) return -1;
        int max = A[0];
        A[0] = A[numElements - 1];
        this.numElements -= 1;
        heapify(A, 0);
        return max;
    }

    public int size() {
        return this.numElements;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // Heapify function maintains the heap property of data structure.
    private void heapify(int[] A, int i) {
        int actual = i + 1;
        int leftChild = left(actual) - 1;
        int rightChild = right(actual) - 1;
        int determine;
        // determine the max/min value of (current, leftchild, rightchild)
        if (isMin) {
            if (leftChild < this.numElements && A[leftChild] < A[i]) determine = leftChild;
            else determine = i;
            if (rightChild < this.numElements && A[rightChild] < A[determine]) determine = rightChild;
        } else {
            if (leftChild < this.numElements && A[leftChild] > A[i]) determine = leftChild;
            else determine = i;
            if (rightChild < this.numElements && A[rightChild] > A[determine]) determine = rightChild;
        }
        // if current is not the min/max value, we need to swap it and recursively call heapify to maintain heap property
        if (determine != i) {
            swap(A, i, determine);
            heapify(A, determine);
        }
    }

    public void increaseKey(int index, int key) {
        int actual = index + 1;
        if (key < this.elements[index]) return;
        this.elements[index] = key;
        while (index > 0 && this.elements[parent(actual) - 1] < this.elements[index]) {
            swap(this.elements, parent(actual) - 1, index);
            index = parent(actual) - 1;
            actual = index + 1;
        }
    }

    public void decreaseKey(int index, int key) {
        int actual = index + 1;
        if (key > this.elements[index]) return;
        this.elements[index] = key;
        while (index > 0 && this.elements[parent(actual) - 1] > this.elements[index]) {
            swap(this.elements, parent(actual) - 1, index);
            index = parent(actual) - 1;
            actual = index + 1;
        }
    }

    // doubling the size of the array, if current array is filled
    private void povecaj() {
        this.maxElements *= 2;
        int[] copy = new int[this.maxElements];
        System.arraycopy(this.elements, 0, copy, 0, this.numElements);
        this.elements = copy;
    }

    // Insert function. Complexity O(logn).
    // To build a desired array we need n insert operations,
    // hence final complexity of O(nlogn)
    public void insert(int key) {
        if (this.numElements == this.maxElements) povecaj();
        this.numElements++;
        if (isMin) {
            this.elements[this.numElements - 1] = Integer.MAX_VALUE;
            decreaseKey(this.numElements - 1, key);
        } else {
            this.elements[this.numElements - 1] = Integer.MIN_VALUE;
            increaseKey(this.numElements - 1, key);
        }
    }

    // Naive list like implentation of toString
    @Override
    public String toString() {
        if (size() == 0) return "[ ]";
        StringBuilder c = new StringBuilder();
        c.append("[");
        for (int i = 0; i < size(); i++) {
            if (i == size() - 1) c.append(this.elements[i] + "]");
            else c.append(this.elements[i] + ", ");
        }
        return c.toString();
    }
}
