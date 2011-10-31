package uebung07;

public class ArrayQueue<T> implements Queue<T> {
	
	private T[] a; 
	private int head;
	private int tail;
	
	@SuppressWarnings("unchecked")
	public ArrayQueue()
	{
		a = (T[]) new Object[1000];
		head = -1;
		tail = 0;
	}

	public void enter(T o) throws QueueException {
		if (tail - head > a.length - 1) throw new QueueException("Queue ist voll.");
		if (head == -1) head = 0;
		a[tail % a.length] = o;
		tail++;
	}

	public T front() throws QueueException {
		if (isEmpty()) throw new QueueException("Queue ist leer.");
		return a[head % a.length];
	}

	public boolean isEmpty() {
		return head == tail || head == -1;
	}

	public void leave() throws QueueException {
		if (isEmpty()) throw new QueueException("Queue ist leer.");
		head++;
	}

}
