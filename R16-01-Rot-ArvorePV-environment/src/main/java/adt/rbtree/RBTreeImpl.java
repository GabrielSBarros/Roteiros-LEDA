package adt.rbtree;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) root) - 1;
	}

	protected int blackHeight(RBNode<T> node) {
		if (node.isEmpty()) {
			return 1;
		}
		int left = blackHeight((RBNode<T>) node.getLeft());
		int right = blackHeight((RBNode<T>) node.getRight());
		int retorno;
		if(left > right) {
			retorno = left;
		}else {
			retorno = right;
		}
		
		int valorNode = 0;
		if (node.getColour().equals(Colour.BLACK)) {
			valorNode = 1;
		}
		return valorNode + retorno;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();
		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by
	 * the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must be
	 * BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) root);
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (node.getColour().equals(Colour.RED)) {
			if (verifyIfChildrenAreRed(node)) {
				return false;
			} else {
				return true && verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
						&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
			}
		}
		return true;
	}

	private boolean verifyIfChildrenAreRed(RBNode<T> node) {
		RBNode<T> left = (RBNode<T>) node.getLeft();
		RBNode<T> right = (RBNode<T>) node.getRight();
		boolean retorno = false;
		if (left.getColour().equals(Colour.RED) || right.getColour().equals(Colour.BLACK)) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	public boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) root);
	}
	
	private boolean verifyBlackHeight(RBNode<T> node) {
		boolean retorno = true; 
		if(!node.isEmpty()) {
			retorno = blackHeight((RBNode<T>)node.getLeft()) == blackHeight((RBNode<T>)node.getRight());
			retorno = retorno && verifyBlackHeight((RBNode<T>)node.getLeft()) && verifyBlackHeight((RBNode<T>) node.getRight());  
		}
		return retorno;
	}

	@Override
	public void insert(T value) {
		if(isEmpty()) {
			RBNode<T> newNode = new RBNode<>();
			newNode.setData(value);
			newNode.setRight(new RBNode<T>());
			newNode.setLeft(new RBNode<T>());
			newNode.setColour(Colour.RED);
			root = newNode;
			fixUpCase1(newNode);
			
		}else
			insert(value, root);		
	}
	
	private void insert(T value, BSTNode<T> node) {
		RBNode<T> newNode = new RBNode<>();
		if(value.compareTo(node.getData()) < 0) {
			if(node.getLeft().isEmpty()) {
				newNode.setData(value);
				newNode.setRight(new RBNode<T>());
				newNode.setLeft(new RBNode<T>());
				newNode.setColour(Colour.RED);
				newNode.setParent(node);
				
				node.setLeft(newNode);
				fixUpCase1(newNode);
			}else
				insert(value, (BSTNode<T>)node.getLeft());
		}else {
			if(node.getRight().isEmpty()) {
				newNode.setData(value);
				newNode.setRight(new RBNode<T>());
				newNode.setLeft(new RBNode<T>());
				newNode.setColour(Colour.RED);
				newNode.setParent(node);
				
				node.setRight(newNode);
				fixUpCase1(newNode);
			}else
				insert(value, (BSTNode<T>)node.getRight());
		}		
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		ArrayList<RBNode<T>> array = new ArrayList<>();
		if(!isEmpty())
			rbPreOrder((RBNode<T>)root, array);
		RBNode<T>[] retorno = new RBNode[size()];
		retorno = array.toArray(retorno);
		return (RBNode<T>[]) retorno;
	}
	
	private void rbPreOrder(RBNode<T> node, ArrayList<RBNode<T>> array) {
		array.add(node);
		if(!node.getLeft().isEmpty()) {
			rbPreOrder((RBNode<T>) node.getLeft(), array);
		}
		if(!node.getRight().isEmpty()) {
			rbPreOrder((RBNode<T>) node.getRight(), array);
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node == root) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour().equals(Colour.RED)) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> p = (RBNode<T>) node.getParent();
		RBNode<T> g = (RBNode<T>) p.getParent();
		RBNode<T> u;
		if (g.getLeft().equals(p)) {
			u = (RBNode<T>) g.getRight();
		} else {
			u = (RBNode<T>) g.getLeft();
		}

		if (u.getColour().equals(Colour.RED)) {
			p.setColour(Colour.BLACK);
			u.setColour(Colour.BLACK);
			g.setColour(Colour.RED);
			fixUpCase1(g);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		
		if (isRightChild(node) && isLeftChild(parent)) {
			leftRotation(parent);
			next = (RBNode<T>)node.getLeft();
		}else if(isLeftChild(node) && isRightChild(parent)) {
			rightRotation(parent);
			next = (RBNode<T>)node.getRight();
		}
		
		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> p = (RBNode<T>) node.getParent();
		RBNode<T> g = (RBNode<T>) p.getParent();
		p.setColour(Colour.BLACK);
		g.setColour(Colour.RED);
		
		if(isLeftChild(node)) {
			rightRotation(g);
		}else {
			leftRotation(g);
		}
	}

	private boolean isLeftChild(BTNode<T> node) {
		BTNode<T> parent = node.getParent();
		if (parent.getLeft().equals(node)) {
			return true;
		}
		return false;
	}

	private boolean isRightChild(BTNode<T> node) {
		BTNode<T> parent = node.getParent();
		if (parent.getRight().equals(node)) {
			return true;
		}
		return false;
	}

	protected void rightRotation(BSTNode<T> node) {
		RBNode<T> newRoot = (RBNode<T>)Util.rightRotation(node);
		if (this.getRoot().equals(node)) {
			root = newRoot;
		}
	}

	protected void leftRotation(BSTNode<T> node) {
		RBNode<T> newRoot = (RBNode<T>)Util.leftRotation(node);
		if (this.getRoot().equals(node)) {
			root = newRoot;
		}

	}
}
