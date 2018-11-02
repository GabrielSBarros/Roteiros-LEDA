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
  public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {
  
     @Override
     public void insert(T element) {
        super.insert(element);
        BSTNode<T> node = search(element);
        rebalanceUp(node);
     }
  
     @Override
     public void remove(T element) {
        BSTNode<T> node = search(element);
        if (!node.isEmpty()) {
           BSTNode<T> parent = (BSTNode<T>) node.getParent();
           super.remove(element);
           rebalanceUp(parent);
        }
     }
  
     // TODO Do not forget: you must override the methods insert and remove
     // conveniently.
  
     // AUXILIARY
     protected int calculateBalance(BSTNode<T> node) {
        return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
     }
  
     // AUXILIARY
     protected void rebalance(BSTNode<T> node) {
        int balance = calculateBalance(node);
        if (Math.abs(balance) > 1) {
           if (balance > 1) {
              if (calculateBalance((BSTNode<T>) node.getLeft()) < 0)
                 leftRotation((BSTNode<T>) node.getLeft());
              rightRotation(node);
           } else {
              if (calculateBalance((BSTNode<T>) node.getRight()) > 0)
                 rightRotation((BSTNode<T>) node.getRight());
              leftRotation(node);
           }
        }
     }
  
     protected void rightRotation(BSTNode<T> node) {
        BSTNode<T> newRoot = Util.rightRotation(node);
        if (this.getRoot().equals(node)) {
           root = newRoot;
        }
     }
  
     protected void leftRotation(BSTNode<T> node) {
        BSTNode<T> newRoot = Util.leftRotation(node);
        if (this.getRoot().equals(node)) {
        	root = newRoot;
        }
  
     }
  
     // AUXILIARY
     protected void rebalanceUp(BSTNode<T> node) {
        if (node != null) {
           rebalance(node);
           rebalanceUp((BSTNode<T>) node.getParent());
        }
     }
  
  }