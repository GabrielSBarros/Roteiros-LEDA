package sorting.divideAndConquer;

import java.util.Random;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex != rightIndex && array.length > 0) {
			Random random = new Random();  
			int indicePivot = random.nextInt(rightIndex-leftIndex) + leftIndex ;  
			Util.swap(array, rightIndex, indicePivot);
			
			T pivot = array[rightIndex];
			int j = leftIndex;
			
			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(pivot) < 0) {
					Util.swap(array, i, j);
					j++;			
				} 
			}
			
			Util.swap(array, rightIndex, j);
			
			if(j > leftIndex)
				sort(array, leftIndex, j - 1);
			if(j < rightIndex)
				sort(array, j + 1, rightIndex);
		}
	}
}
