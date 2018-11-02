package adt.btree;

import java.util.ArrayList;
import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

   protected BNode<T> root;
   protected int order;

   public BTreeImpl(int order) {
      this.order = order;
      this.root = new BNode<T>(order);
   }

   @Override
   public BNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return this.root.isEmpty();
   }

   @Override
   public int height() {
      return height(this.root) - 1;
   }

   private int height(BNode<T> node) {
      if (node.isEmpty()) {
         return 0;
      }
      int maiorAltura = 0;
      int altura;
      LinkedList<BNode<T>> children = node.getChildren();
      for (int i = 0; i < children.size(); i++) {
         altura = height(children.get(i));
         if (altura > maiorAltura)
            maiorAltura = altura;
      }

      return 1 + maiorAltura;

   }

   @Override
   public BNode<T>[] depthLeftOrder() {
      ArrayList<BNode<T>> array = new ArrayList<>();
      ArrayList<BNode<T>> aux = new ArrayList<>();
      array.add(root);

      aux.add(root);
      while (!aux.isEmpty()) {
         array.addAll(aux.get(0).getChildren());
         aux.addAll(aux.get(0).getChildren());
         aux.remove(0);
      }
      BNode<T>[] nodes = new BNode[array.size()];
      array.toArray(nodes);

      return nodes;
   }

   public void preOrder() {
      preOrder(root);
   }

   private void preOrder(BNode<T> node) {
      System.out.println(node);
      LinkedList<BNode<T>> children = node.getChildren();
      for (BNode<T> bNode : children) {
         preOrder(bNode);
      }
   }

   @Override
   public int size() {
      return size(root);
   }

   public int size(BNode<T> node) {
      if (node.isLeaf())
         return node.size();
      int size = 0;
      LinkedList<BNode<T>> children = new LinkedList<>();
      for (int i = 0; i < children.size(); i++) {
         size += size(children.get(i));
      }
      return node.size() + size;
   }

   @Override
   public BNodePosition<T> search(T element) {
      return search(element, root);
   }

   public BNodePosition<T> search(T element, BNode<T> node) {
      if (node.isEmpty())
         return new BNodePosition<T>();

      int index = 0;
      LinkedList<T> elements = node.getElements();
      while (index < order - 1 && elements.get(index).compareTo(element) < 0) {
         index++;
      }

      if (elements.get(index).equals(element))
         return new BNodePosition<T>(node, index);

      return search(element, node.getChildren().get(index));
   }

   @Override
   public void insert(T element) {
      insert(element, root);
   }

   public void insert(T element, BNode<T> node) {
      if (node.isFull()) {

         split(node);
         //insert(element, node.getParent());
         node = node.getParent();
      }

      if (node.isLeaf()) {
         node.addElement(element);
      } else {
         int index = 0;
         LinkedList<T> elements = node.getElements();
         while (index < elements.size() && elements.get(index).compareTo(element) < 0) {
            index++;
         }

         //System.out.println(node);
         //System.out.println(node.getChildren());
         //System.out.println(index);
         if (!(index != elements.size() && elements.get(index).equals(element)))
            insert(element, node.getChildren().get(index));
      }

   }

   private void split(BNode<T> node) {
      //System.out.println(node);
      int mediana = (order - 1) / 2;

      BNode<T> leftChild = new BNode<>(order);
      BNode<T> rightChild = new BNode<>(order);

      setChilds(node, leftChild, rightChild, mediana);
      //System.out.println("left " + leftChild);
      //System.out.println("right " + rightChild);
      T element = node.getElementAt(mediana);

      node.removeElement(element);
      promote(element, node, leftChild, rightChild);

   }

   private void setChilds(BNode<T> node, BNode<T> leftChild, BNode<T> rightChild, int mediana) {
      //System.out.println("setChild" + node);
      for (int i = 0; i < mediana; i++) {
         leftChild.addElement(node.getElementAt(i));
      }

      //System.out.println("for 1");
      for (int i = mediana + 1; i < order - 1; i++) {
         rightChild.addElement(node.getElementAt(i));
      }

      //System.out.println("for 2");

      LinkedList<BNode<T>> children = node.getChildren();

      int index = 0;
      int position = 0;
      while (index < children.size() && index <= (node.getMaxChildren() / 2) - 1) {

         //System.out.println("left " + leftChild);

         leftChild.addChild(position, children.get(index));
         //System.out.println(leftChild.getChildren());
         index++;
         position++;
      }
      //System.out.println("1 while");
      position = 0;
      while (index < children.size() && index < node.getMaxChildren()) {
         rightChild.addChild(position, children.get(index));
         index++;
         position++;
      }
      //System.out.println("while 2");
   }

   private void promote(T element, BNode<T> node, BNode<T> leftChild, BNode<T> rightChild) {
      BNode<T> parent = node.getParent();

      if (parent == null) {
         parent = new BNode<>(order);
         node.setParent(parent);
         root = parent;
      }

      parent.addElement(element);
      parent.removeChild(node);

      int index = parent.getElements().indexOf(element);

      parent.addChild(index, leftChild);
      parent.addChild(index + 1, rightChild);

   }

   // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
   @Override
   public BNode<T> maximum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public BNode<T> minimum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public void remove(T element) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

}
