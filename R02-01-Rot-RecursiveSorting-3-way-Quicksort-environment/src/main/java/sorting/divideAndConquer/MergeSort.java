package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	public void merge(T[] array, int array1LeftIndex, int meio, int array2RightIndex) {
		int array1Index = array1LeftIndex;
		int array2Index = meio + 1;
		T[] arrayAux = (T[]) new Comparable[array2RightIndex - array1LeftIndex + 1];
		int arrayAuxIndex = 0;
		
		while(array1Index <= meio && array2Index <= array2RightIndex) {
			if(array[array1Index].compareTo(array[array2Index]) < 0) {
				arrayAux[arrayAuxIndex] = array[array1Index];
				array1Index++;
			}else {
				arrayAux[arrayAuxIndex] = array[array2Index];
				array2Index++;
			}
			arrayAuxIndex++;
		}
		
		for (int i = array1Index; i <= meio; i++) {
			arrayAux[arrayAuxIndex] = array[i];
			arrayAuxIndex++;
		}
		
		
		for (int i = array2Index; i <= array2RightIndex; i++) {
			arrayAux[arrayAuxIndex] = array[i];
			arrayAuxIndex++;
		}
		
		
		for (int i = 0; i < arrayAux.length; i++) {
			array[array1LeftIndex + i] = arrayAux[i];
		}
		
		
		
	}

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && array.length > 0) {
			int meio = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}
	}
	
}
