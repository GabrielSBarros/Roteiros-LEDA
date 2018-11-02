package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		final double FATOR = 1.25;
		int gap = (int) ((rightIndex - leftIndex + 1) / FATOR);
		while(gap >= 1) {
			for (int i = leftIndex + gap; i <= rightIndex; i++) {
				if(array[i].compareTo(array[i - gap]) < 0) {
					Util.swap(array, i, i - gap);
				}
			}
			gap = (int)(gap / FATOR);
		}
	}
}
