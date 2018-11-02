package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		super.head = new DoubleLinkedListNode<T>();
		last = new DoubleLinkedListNode<T>();
	}

	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> nodo = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(),  new DoubleLinkedListNode<T>());
		
		if (isEmpty()) {
			head = nodo;
		}else {
			SingleLinkedListNode<T> aux = head;
			
			while(!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			nodo.setPrevious((DoubleLinkedListNode<T>)aux);
			aux.setNext(nodo);
		}
		last = nodo;
	}
	
	@Override
	public void remove(T element) {
		if(head.getData().equals(element) || isEmpty()) {
			head = head.getNext();
		}else {
			DoubleLinkedListNode<T> anterior = (DoubleLinkedListNode<T>)head;
			DoubleLinkedListNode<T> aux = anterior;
			
			while(!aux.getNext().isNIL()) {
				anterior = aux;
				aux = (DoubleLinkedListNode<T>) aux.getNext();
				if(aux.getData().equals(element)) {
					anterior.setNext(aux.getNext());
					aux = (DoubleLinkedListNode<T>) aux.getNext();
					aux.setPrevious(anterior);
					break;
				}
			}
		}	
	}
	
	@Override
	public void insertFirst(T element) {
		super.head = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) super.head, new DoubleLinkedListNode<T>());
	}

	@Override
	public void removeFirst() {
		super.head = super.head.getNext();
	}

	@Override
	public void removeLast() {
			DoubleLinkedListNode<T> aux = last.getPrevious();
			aux.setNext(new DoubleLinkedListNode<T>());
			last = aux;
			if(size() == 1) {
				head = last;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
