package adt.stack;

import adt.linkedList.RecursiveDoubleLinkedListImpl;


public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected RecursiveDoubleLinkedListImpl<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
		}
		top.insert(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		T retorno = top.getLast().getData();
		top.removeLast();
		return retorno;
	}

	@Override
	public T top() {
		return top.getLast().getData();
	}

	@Override
	public boolean isEmpty() {
		return top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return top.size() == size;
	}

}
