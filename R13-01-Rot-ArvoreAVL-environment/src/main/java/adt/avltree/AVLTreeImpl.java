package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if(Math.abs(balance) > 1) {
			BSTNode<T> nodeUp = new BSTNode<>();
			if(balance > 1) {
				if(node.getLeft().getLeft().isEmpty()) {
					Util.leftRotation((BSTNode<T>)node.getLeft());
				}
				nodeUp = Util.rightRotation(node);
				
			}else {
				if(node.getRight().getRight().isEmpty()) {
					Util.rightRotation((BSTNode<T>) node.getRight());
				}
				nodeUp = Util.leftRotation(node);
			}
			if(node == getRoot()) {
				root = nodeUp;
			}
			
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>)node.getParent();
		while(parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>)parent.getParent();
		}
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
		if(element.compareTo(node.getData()) < 0) {
			if(node.getLeft().isEmpty()) {
				node.setLeft(new BSTNode.Builder()
									.data(element)
									.parent(node)
									.left(new BSTNode<T>())
									.right(new BSTNode<T>())
									.build());
				rebalanceUp(node);
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
				rebalanceUp(node);
			}else
				insert(element, (BSTNode<T>)node.getRight());
		}		
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
			rebalanceUp(node);
			
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
				
				rebalanceUp(node);
			}
		}		
	}
}
