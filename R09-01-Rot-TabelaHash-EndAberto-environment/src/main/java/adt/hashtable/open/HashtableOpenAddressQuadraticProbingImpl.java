package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(this.elements == table.length) {
			throw new HashtableOverflowException();
		}
		
		if (getHashFunction() instanceof HashFunctionQuadraticProbing) {
			
			HashFunctionQuadraticProbing<T> hashFunction = (HashFunctionQuadraticProbing<T>)getHashFunction();
			int probe = 0;
			int key = hashFunction.hash(element, probe); 
			while(this.table[key] != null && !this.table[key].equals(element)) {
				probe++;
				key =  hashFunction.hash(element, probe);
				COLLISIONS++;
			}
			
			this.table[key] = element;
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		if (getHashFunction() instanceof HashFunctionQuadraticProbing) {
			HashFunctionQuadraticProbing<T> function = ((HashFunctionQuadraticProbing<T>) getHashFunction());
			
			int probe = 0;
			int firstKey = -1;
			int key = function.hash(element, probe);
			
			while(table[key] != null && key!= firstKey) {
				if(firstKey == -1) firstKey = key;
				if(table[key].equals(element)) {
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
		if (getHashFunction() instanceof HashFunctionQuadraticProbing) {
			HashFunctionQuadraticProbing<T> function = ((HashFunctionQuadraticProbing<T>) getHashFunction());
			int probe = 0;
			int firstKey = -1;
			int key =  function.hash(element, probe);
			
			while(table[key] != null && key != firstKey) {	
				if(firstKey == -1) firstKey = key;
				if(table[key].equals(element)) {
					return (T)table[key];
				}
				probe++;
				key = function.hash(element, probe);
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		if (getHashFunction() instanceof HashFunctionQuadraticProbing) {
			HashFunctionQuadraticProbing<T> function = ((HashFunctionQuadraticProbing<T>) getHashFunction());		
			int probe = 0;
			int key = function.hash(element, probe);
			
			while(table[key] != null) {
				key = function.hash(element, probe);
				
				if(table[key].equals(element)) {
					return key;
				}
				probe++;
			}
		}
		return -1;
	}
}
