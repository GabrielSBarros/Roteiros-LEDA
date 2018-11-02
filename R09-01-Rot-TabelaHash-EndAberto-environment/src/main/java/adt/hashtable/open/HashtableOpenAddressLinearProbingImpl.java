package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
      super(size);
      hashFunction = new HashFunctionLinearProbing<T>(size, method);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (this.elements == table.length) {
         throw new HashtableOverflowException();
      }

      if (getHashFunction() instanceof HashFunctionLinearProbing) {

         HashFunctionLinearProbing<T> hashFunction = (HashFunctionLinearProbing<T>) getHashFunction();
         int probe = 0;
         int key = hashFunction.hash(element, probe);
         while (this.table[key] != null && !this.table[key].equals(element)) {
            probe++;
            key = hashFunction.hash(element, probe);
            COLLISIONS++;
         }

         this.table[key] = element;
         elements++;
      }
   }

   @Override
   public void remove(T element) {
      if (getHashFunction() instanceof HashFunctionLinearProbing) {
         HashFunctionLinearProbing<T> function = ((HashFunctionLinearProbing<T>) getHashFunction());

         int probe = 0;
         int key = function.hash(element, probe);

         while (table[key] != null) {

            if (table[key].equals(element)) {
               table[key] = new DELETED();
               elements--;
               break;
            }

            probe++;
            key = function.hash(element, probe);

         }
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public T search(T element) {
      if (getHashFunction() instanceof HashFunctionLinearProbing) {
         HashFunctionLinearProbing<T> function = ((HashFunctionLinearProbing<T>) getHashFunction());
         int probe = 0;
         int key = function.hash(element, probe);

         while (table[key] != null) {
            if (table[key].equals(element)) {
               return (T) table[key];
            }
            probe++;
            key = function.hash(element, probe);
         }
      }
      return null;
   }

   @Override
   public int indexOf(T element) {
      if (getHashFunction() instanceof HashFunctionLinearProbing) {
         HashFunctionLinearProbing<T> function = ((HashFunctionLinearProbing<T>) getHashFunction());
         int probe = 0;
         int key = function.hash(element, probe);

         while (table[key] != null) {
            key = function.hash(element, probe);

            if (table[key].equals(element)) {
               return key;
            }
            probe++;
         }
      }
      return -1;
   }

}
