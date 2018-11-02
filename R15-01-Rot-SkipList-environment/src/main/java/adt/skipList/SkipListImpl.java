package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

   protected SkipListNode<T> root;
   protected SkipListNode<T> NIL;

   protected int maxHeight;

   protected double PROBABILITY = 0.5;

   public SkipListImpl(int maxHeight) {
      this.maxHeight = maxHeight;
      root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
      NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
      connectRootToNil();
   }

   /**
    * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
    * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
    * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
    * metodo deve conectar apenas o forward[0].
    */
   private void connectRootToNil() {
      for (int i = 0; i < maxHeight; i++) {
         root.forward[i] = NIL;
      }
   }

   @Override
   public void insert(int key, T newValue, int height) {
      SkipListNode<T> node = search(key);
      if (node != null) {
         node.setValue(newValue);
      } else {
         SkipListNode<T>[] nodos = search(key, height);

         SkipListNode<T> novoNodo = new SkipListNode<T>(key, height, newValue);
         SkipListNode<T>[] novoNodoForward = novoNodo.getForward();

         for (int i = 0; i < nodos.length; i++) {
            novoNodoForward[i] = nodos[i].getForward(i);
            SkipListNode<T>[] aux = nodos[i].getForward();
            aux[i] = novoNodo;
         }
      }
   }

   private SkipListNode<T>[] search(int key, int height) {
      SkipListNode<T>[] retorno = new SkipListNode[height];
      SkipListNode<T> aux;
      for (int i = height - 1; i >= 0; i--) {
         aux = root;
         while (aux.getForward(i).getKey() < key) {
            aux = aux.getForward(i);
         }
         retorno[i] = aux;
      }

      return retorno;

   }

   @Override
   public void remove(int key) {
      SkipListNode<T> nodo = search(key);
      if (nodo != null) {
         SkipListNode<T>[] nodos = search(key, nodo.height());
         for (int i = nodo.height() - 1; i >= 0; i--) {
            nodos[i].getForward()[i] = nodo.getForward(i);
         }
      }
   }

   @Override
   public int height() {
      int maxHeight = 0;
      SkipListNode<T> aux = root.getForward(0);
      while (aux.getKey() != Integer.MAX_VALUE) {
         if (aux.height() > maxHeight) {
            maxHeight = aux.height();
         }
      }
      return maxHeight;

   }

   @Override
   public SkipListNode<T> search(int key) {
      SkipListNode<T> aux = root;
      for (int i = maxHeight - 1; i >= 0; i--) {
         while (aux.getForward(i).getKey() <= key) {
            aux = aux.getForward(i);
         }
      }

      if (aux.getKey() != key)
         aux = null;

      return aux;
   }

   @Override
   public int size() {
      int size = 0;
      SkipListNode<T> aux = root.getForward(0);
      while (aux.getKey() != Integer.MAX_VALUE) {
         aux = aux.getForward(0);
         size++;
      }
      return size;
   }

   @Override
   public SkipListNode<T>[] toArray() {
      int size = size() + 2;
      SkipListNode<T>[] retorno = new SkipListNode[size];
      SkipListNode<T> aux = root;

      for (int i = 0; i < size; i++) {
         retorno[i] = aux;
         aux = aux.getForward(0);
      }
      return retorno;
   }

}
