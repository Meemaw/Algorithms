import java.lang.reflect.Array;

public class ArrayList<T> {

    private T[] elements;
    private int numElements;
    private int maxElements;
    private Class c;

    public ArrayList(Class<T> c) {
        this(c,10);
    }

    private ArrayList(Class<T> c, int size) {
        this.elements = (T[]) Array.newInstance(c,size);
        this.numElements = 0;
        this.maxElements = size;
        this.c = c;
    }

    public void add(T element) {
        this.elements[numElements++] = element;
        if(numElements == maxElements) {
            maxElements*=2;
            T[] copy = (T[]) Array.newInstance(this.c, this.maxElements);
            System.arraycopy(this.elements, 0, copy, 0, size());
            this.elements = copy;
        }
    }

    public void remove(int index) {
        if(index >= size()) return;
        else {
            for(int i = index+1; i < size(); i++) {
                this.elements[i-1] = this.elements[i];
            }
        }
        numElements--;
    }

    public int size() {
        return numElements;
    }

    public boolean isEmpty() {
        return this.numElements == 0;
    }

    @Override
    public String toString() {
        if(size() == 0) return "[ ]";
        StringBuilder c = new StringBuilder();
        c.append("[");
        for(int i = 0; i < size(); i++) {
            if(i == size()-1) c.append(this.elements[i] + "]");
            else c.append(this.elements[i] + ", ");
        }
        return c.toString();
    }
}