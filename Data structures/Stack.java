import java.lang.reflect.Array;

public class Stack<T> {

    private Class c;
    private T[] elements;
    private int numElements;
    private int maxElements;

    public Stack(Class c) {
        this(c,10);
    }

    private Stack(Class c, int size) {
        this.c = c;
        this.maxElements = size;
        this.elements = (T []) Array.newInstance(this.c, this.maxElements);
        this.numElements = 0;
    }

    public void push(T element) {
        this.elements[numElements++] = element;
        if(this.isFull()) {
            maxElements *= 2;
            T[] copy = (T[]) Array.newInstance(this.c, this.maxElements);
            System.arraycopy(this.elements, 0, copy, 0, size());
            this.elements = copy;
        }
    }

    public T pop() {
        if(numElements == 0) return null;
        T output = this.elements[numElements-1];
        this.numElements--;
        return output;
    }

    public T peek() {
        if(numElements == 0) return null;
        return this.elements[numElements-1];
    }

    public boolean isFull() {
        return this.numElements == this.maxElements;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.numElements;
    }
}