package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		}
		
		if(isEmpty()) {
			head = 0;
			tail = 0;
		}else {
			tail = (tail + 1) % array.length;		
		}
		
		array[tail] = element;
		elements++;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T retorno = array[head];
		if(elements == 1) {
			head = -1;
			tail = -1;
		}else {
			head = (head + 1) % array.length;			
		}
		elements--;
		
		return retorno;
	}

	@Override
	public T head() {
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return head == -1;
	}

	@Override
	public boolean isFull() {
		return ((tail + 1) % array.length == head);
	}

}
