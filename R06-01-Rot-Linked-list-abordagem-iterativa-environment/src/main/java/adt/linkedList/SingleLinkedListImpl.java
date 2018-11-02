package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		
		int contador = 1;
		SingleLinkedListNode<T> aux = head;
		
		while(!aux.getNext().isNIL()) {
			contador++;
			aux = aux.getNext();
		}
		return contador;
	}

	@Override
	public T search(T element) {
		if(isEmpty()) {
			return null;
		}
		
		SingleLinkedListNode<T> aux = head;
		do {
			if (aux.getData().equals(element)){
				return aux.getData();
			}
			aux = aux.getNext();
		}while(!aux.isNIL());
		
		return null;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> nodo = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
		if (isEmpty()) {
			head = nodo;
		}else {
			SingleLinkedListNode<T> aux = head;
			
			while(!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			
			aux.setNext(nodo);
		}
	}

	@Override
	public void remove(T element) {
		if(head.getData().equals(element) || isEmpty()) {
			head = head.getNext();
		}else {
			SingleLinkedListNode<T> anterior = head;
			SingleLinkedListNode<T> aux = head;
			
			while(!aux.getNext().isNIL()) {
				anterior = aux;
				aux = aux.getNext();
				if(aux.getData().equals(element)) {
					anterior.setNext(aux.getNext());
					break;
				}
			}
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		int index = 0;
		while(!aux.isNIL()) {
			array[index] = aux.getData();
			aux = aux.getNext();
			index++;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
