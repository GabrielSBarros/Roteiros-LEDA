package sorting.divideAndConquer.threeWayQuicksort;

import java.util.Random;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
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
			
			int k = j + 1;
			for (int i = k; i < rightIndex; i++) {
				if (array[i].compareTo(array[j]) == 0) {
					Util.swap(array, i, k);
					k++;
				}
			}
			if(j > leftIndex)
				sort(array, leftIndex, j - 1);
			if(k < rightIndex)
				sort(array, k , rightIndex);
		}
	}

}
