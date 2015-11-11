
public class Queue {
	
	private class QueueElement {
		
		public Object element;
		public QueueElement next;
		
		public QueueElement(Object element) {
			this.element = element;
			next = null;
		}
	}
	
	private QueueElement front;
	private QueueElement end;
	
	
	public Queue() {
		initialaze();
	}
	
	private void initialaze() {
		front = null;
		end = null;
	}
	
	public boolean empty() {
		return front == null;
	}
	
	public void enqueue(Object obj) {
		QueueElement novi = new QueueElement(obj);
		if(empty()){
			front = novi;
			end = novi;
		}
		else {
			end.next = novi;
			end = novi;
		}
	}
	
	public QueueElement dequeue() {
		if(empty()) return null;
		QueueElement temp = front;
		front = front.next;
		return temp;
	}
	
	public int size() {
		int acc = 0;
		QueueElement temp = front;
		while(temp != null) {
			acc++;
			temp = temp.next;
		}
		return acc;
	}

}
