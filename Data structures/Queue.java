package data_structures_tested;

/**
 * Created by Meemaw on 09/02/16.
 */
public class Queue<T> {


    private static class QueueElement<T> {
        private QueueElement next;
        private T element;

        public QueueElement(T element) {
            this.element = element;
            this.next = null;
        }
    }

    private QueueElement front;
    private QueueElement back;


    public Queue() {
        this.front = null;
        this.back = null;
    }

    public boolean isEmpty() {
        return this.front == null;
    }

    public void enqueue(T element) {
        QueueElement input = new QueueElement(element);
        if(isEmpty()) {
            this.front = input;
            this.back = input;
        } else {
            this.back.next = input;
            this.back = input;
        }
    }


    public T dequeue() {
        if(isEmpty()) return null;
        QueueElement temp = this.front;
        this.front = this.front.next;
        return (T) temp.element;
    }


    public int size() {
        if(isEmpty()) return 0;
        QueueElement temp = this.front;
        int sum = 0;
        while(temp != null) {
            sum += 1;
            temp = temp.next;
        }
        return sum;
    }

}
