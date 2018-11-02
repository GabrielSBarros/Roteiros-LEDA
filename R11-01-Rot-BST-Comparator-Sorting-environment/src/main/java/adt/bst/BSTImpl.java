package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root) - 1;		
	}
	
	private int height(BSTNode<T> node) {
		if(node.isEmpty()) {
			return 0;
		}else {
			int subTreeLeft = height((BSTNode<T>) node.getLeft());
			int subTreeRight = height((BSTNode<T>) node.getRight());
			if(subTreeLeft > subTreeRight) {
				return 1 + subTreeLeft;
			}else {
				return 1 + subTreeRight;
			}
		}
		
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, root);
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node){
		if(node.isEmpty() || node.getData().equals(element)) {
			return node;
		}
		
		if(element.compareTo(node.getData()) < 0) {
			return search(element, (BSTNode<T>)node.getLeft());
		}else {
			return search(element, (BSTNode<T>)node.getRight());
		}
	}

	@SuppressWarnings("unchecked")
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
		if(element.compareTo(node.getData()) < 0) {
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
	public BSTNode<T> maximum() {
		return maximum(root);	
	}
	
	public BSTNode<T> maximum(BSTNode<T> node) {
		if(node.isEmpty()) {
			return null;
		}
		
		BSTNode<T> aux = node;
		while(!aux.getRight().isEmpty()) {
			aux = (BSTNode<T>)aux.getRight();
		}
		
		return aux;	
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);	
	}
	
	public BSTNode<T> minimum(BSTNode<T> node) {
		if(node.isEmpty()) {
			return null;
		}
		
		BSTNode<T> aux = node;
		while(!aux.getLeft().isEmpty()) {
			aux = (BSTNode<T>)aux.getLeft();
		}
		return aux;	
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
			node = minimum(node);	
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
				if(node.getData().compareTo(element) < 0) {
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
	public void remove(T element) {
		BSTNode<T> aux = search(element);
		if(size() == 1) {
			root.setData(null);
		}else if(!aux.isEmpty()) {
			remove(aux);
		}
	}
	
	private void remove(BSTNode<T> node) {
		if(node.isLeaf()) {
			
			BSTNode<T> aux = (BSTNode<T>)node.getParent();
			if(aux.getRight().equals(node)) {
				aux.setRight(new BSTNode<T>());
			}else {
				aux.setLeft(new BSTNode<T>());
			}
			
		}else {
			if(!node.getRight().isEmpty() && !node.getLeft().isEmpty()) { // dois filhos
				
				BSTNode<T> aux = sucessor(node.getData(), node);
				node.setData(aux.getData());
				remove(aux);			
				
			}else {			
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				
				if(parent == null) {
					this.root = getFilho(node);
					this.root.setParent(null);
				}else if(parent.getRight()!= null && node.equals(parent.getRight())) {
					parent.setRight(getFilho(node));
					parent.getRight().setParent(parent);
				}else {
					parent.setLeft(getFilho(node));
					parent.getLeft().setParent(parent);
				}	
			}
		}		
	}
	

	private BSTNode<T> getFilho(BSTNode<T> node){
		if(!node.getRight().isEmpty()) { // um filho a direita
			return (BSTNode<T>) node.getRight();
		}else if(!node.getLeft().isEmpty()) { // um filho a esquerda
			return ((BSTNode<T>) node.getLeft());
		}
		return null;
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> array = new ArrayList<>();
		if(!isEmpty())
			preOrder(root, array);
		Comparable<?>[] retorno = new Comparable<?>[size()];
		retorno = array.toArray(retorno);
		return (T[]) retorno;
	}
	
	private void preOrder(BSTNode<T> node, ArrayList<T> array) {
		array.add(node.getData());
		if(!node.getLeft().isEmpty()) {
			preOrder((BSTNode<T>) node.getLeft(), array);
		}
		if(!node.getRight().isEmpty()) {
			preOrder((BSTNode<T>) node.getRight(), array);
		}
		
	}

	@Override
	public T[] order() {
		ArrayList<T> array = new ArrayList<>();
		if(!isEmpty())
			order(root, array);
		Comparable<?>[] retorno = new Comparable<?>[size()];
		array.toArray(retorno);
		return (T[]) retorno;
	}
	
	private void order(BSTNode<T> node, ArrayList<T> array) {
		if(!node.getLeft().isEmpty()) {
			order((BSTNode<T>) node.getLeft(), array);
		}
		array.add(node.getData());
		if(!node.getRight().isEmpty()) {
			order((BSTNode<T>) node.getRight(), array);
		}
		
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> array = new ArrayList<>();
		if(!isEmpty())
			postOrder(root, array);
		Comparable<?>[] retorno = new Comparable<?>[size()];
		array.toArray(retorno);
		return (T[]) retorno;
	}
	
	private void postOrder(BSTNode<T> node, ArrayList<T> array) {
		if(!node.getLeft().isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), array);
		}
		if(!node.getRight().isEmpty()) {
			postOrder((BSTNode<T>) node.getRight(), array);
		}
		array.add(node.getData());
		
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
