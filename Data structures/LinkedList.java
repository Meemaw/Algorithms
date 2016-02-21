package data_structures_tested;

/**
 * Created by Meemaw on 09/02/16.
 */
public class LinkedList<T>  {



    private static class LinkedListElement<T> {

        private T element;
        private LinkedListElement next;

        private LinkedListElement(T elem) {
            this.element = elem;
        }
    }


    private LinkedListElement front;
    private LinkedListElement back;


    public LinkedList() {
        front = null;
        back = null;
    }


    public void add(T element) {
        LinkedListElement c = new LinkedListElement(element);
        if(isEmpty()) {
            this.front = c;
            this.back = c;
        } else {
            back.next = c;
            back = c;
        }
    }


    public void remove(int index) {
        int length = size();
        if(index >= length) return;
        if(index == 0) this.front = this.front.next;
        else {
            int curr = 0;
            LinkedListElement c = this.front;
            while(c != null) {
                if(curr + 1 == index) {
                    if(c.next.next == null) this.back = c;
                    c.next = c.next.next;
                    break;
                }
                curr += 1;
                c = c.next;
            }
        }
    }

    public int size() {
        LinkedListElement c = this.front;
        int sum = 0;
        while(c != null) {
            sum += 1;
            c = c.next;
        }
        return sum;
    }

    public String toString() {
        if(size() == 0) return "[ ]";
        LinkedListElement c = front;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while(c != null) {
            if(c.next != null) builder.append(c.element + ", ");
            else builder.append(c.element);
            c = c.next;
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean isEmpty() {
        return front == null;
    }
}
