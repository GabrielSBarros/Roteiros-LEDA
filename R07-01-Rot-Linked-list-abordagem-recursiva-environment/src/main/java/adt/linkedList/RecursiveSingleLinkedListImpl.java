package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return isNull();
	}

	@Override
	public int size() {
		if(isNull()) {
			return 0;
		}
		return 1 + getNext().size();
	}

	@Override
	public T search(T element) {
		if(isNull()) {
			return null;
		}
		if(data.equals(element)) {
			return data;
		}
		return getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if(isNull()) {
			setData(element);
			setNext(new RecursiveSingleLinkedListImpl<T>());
		}else {
			getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(!isNull()) {
			if(data.equals(element)) {
				setData(getNext().getData());
				setNext(getNext().getNext());
			}else {
				getNext().remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		
		if(!isNull()) {
			RecursiveSingleLinkedListImpl<T> aux = this;
				
			for (int i = 0; i < array.length; i++) {
				array[i] = aux.getData();
				aux = aux.getNext();
			}
		}
		
		return array;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
	
	public boolean isNull() {
		return data == null;
	}
	
	public RecursiveSingleLinkedListImpl<T> getLast() {
		if(getNext().isNull()) {
			return this;
		}else {
			return getNext().getLast();
		}

	}

}
