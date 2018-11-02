package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	private void inserirArray(T[] array) {
		for (int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
	}

	@Override
	public T[] sort(T[] array) {
		inserirArray(array);
		return this.order();
	}

	@Override
	public T[] reverseOrder() {
		ArrayList<T> array = new ArrayList<>();
		if(!isEmpty())
			reverseOrder(root, array);
		Comparable<?>[] retorno = new Comparable<?>[size()];
		array.toArray(retorno);
		return (T[]) retorno;
	}
	
	private void reverseOrder(BSTNode<T> node, ArrayList<T> array) {
		if(!node.getRight().isEmpty()) {
			reverseOrder((BSTNode<T>) node.getRight(), array);
		}
		array.add(node.getData());
		if(!node.getLeft().isEmpty()) {
			reverseOrder((BSTNode<T>) node.getLeft(), array);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public void insert(T element) {
		if(isEmpty()) {
			root = (BSTNode<T>) new BSTNode.Builder<T>()
					.data(element)
					.left(new BSTNode<T>())
					.right(new BSTNode<T>())
					.build();
		}else
			insert(element, root);
	}
	
	@SuppressWarnings("unchecked")
	private void insert(T element, BSTNode<T> node) {
		if(this.comparator.compare(element, node.getData()) < 0) {
			if(node.getLeft().isEmpty()) {
				node.setLeft(new BSTNode.Builder()
									.data(element)
									.parent(node)
									.left(new BSTNode<T>())
									.right(new BSTNode<T>())
									.build());
			}else
				insert(element, (BSTNode<T>)node.getLeft());
		}else {
			if(node.getRight().isEmpty()) {
				node.setRight(new BSTNode.Builder()
						.data(element)
						.parent(node)
						.left(new BSTNode<T>())
						.right(new BSTNode<T>())
						.build());
			}else
				insert(element, (BSTNode<T>)node.getRight());
		}		
	}
	
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> aux = search(element);	
		if(aux.isEmpty()) {
			return null;
		}
		return sucessor(element, aux);
	}
	

	private BSTNode<T> sucessor(T element, BSTNode<T> node){
		if(!node.getRight().isEmpty()) {
			node = (BSTNode<T>)node.getRight();
			node = this.minimum(node);	
		}else {	
			while(node != null) {			
				if(node.getData().compareTo(element) > 0) {
					break;
				}
				node = (BSTNode<T>) node.getParent();
			}
		}
			
		return node;
	}
	
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> aux = search(element);
		if(aux.isEmpty()) {
			return null;
		}
		
		return predecessor(element, aux);
		
	}

	
	private BSTNode<T> predecessor(T element, BSTNode<T> node){
		if(node.getLeft().isEmpty()) {
			while(node != null) {			
				if(this.comparator.compare(node.getData(), element) < 0) {
					break;
				}
				node = (BSTNode<T>) node.getParent();
			}
			
		}else {
			node = (BSTNode<T>)node.getLeft();
			node = maximum(node);
		}
		return node;
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return search(element, root);
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node){
		if(node.isEmpty() || node.getData().equals(element)) {
			return node;
		}
		
		if(this.comparator.compare(element, node.getData()) < 0) {
			return search(element, (BSTNode<T>)node.getLeft());
		}else {
			return search(element, (BSTNode<T>)node.getRight());
		}
	}
	
}
