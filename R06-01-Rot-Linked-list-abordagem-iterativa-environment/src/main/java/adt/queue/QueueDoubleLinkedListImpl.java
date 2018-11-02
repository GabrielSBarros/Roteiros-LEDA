package adt.queue;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedListImpl<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(list.size() == size) {
			throw new QueueOverflowException();
		}
		list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(list.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T retorno = list.getHead().getData();
		list.removeFirst();
		return retorno;
	}

	@Override
	public T head() {
		return list.getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return list.size() == size;
	}

}
