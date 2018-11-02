package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if(isNull()) {
			setData(element);
			setNext(new RecursiveDoubleLinkedListImpl<T>());
		}else {
			RecursiveDoubleLinkedListImpl<T> aux = (RecursiveDoubleLinkedListImpl<T>)getNext();
			
			if(aux.isNull()) {
				aux.setData(element);
				aux.setNext(new RecursiveDoubleLinkedListImpl<T>());
				aux.setPrevious(this);
			}else {
				aux.insert(element);
			}		
		}
	}
	
	@Override
	public void remove(T element) {
		if(!isNull()) {
			if(data.equals(element)) {
				RecursiveDoubleLinkedListImpl<T> previous = getPrevious();
				RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) getNext();
				previous.setNext(next);
				next.setPrevious(previous);
			}else {
				getNext().remove(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<T>(getData(), getNext(), this);
		setNext(aux);
		setData(element);
		
		aux = (RecursiveDoubleLinkedListImpl<T>) aux.getNext();
		aux.setPrevious((RecursiveDoubleLinkedListImpl<T>) getNext());
	}

	@Override
	public void removeFirst() {
		setData(getNext().getData());
		setNext(getNext().getNext());
	}

	@Override
	public void removeLast() {
		RecursiveDoubleLinkedListImpl<T> aux = this;
		while(!aux.getNext().isNull()) {
			aux = (RecursiveDoubleLinkedListImpl<T>) aux.getNext();
		}
		if(aux.equals(this)) {
			setData(null);
		}else {
			aux = aux.getPrevious();
			aux.setNext(new RecursiveDoubleLinkedListImpl<T>());
		}
	}
	
	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
