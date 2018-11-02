package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0 && leftIndex <= rightIndex) {
			int maior = array[leftIndex];
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if(array[i] > maior) {
					maior = array[i];
				}
			}
			
			Integer[] c = new Integer[maior + 1];
			for (int i = 0; i < c.length; i++) {
				c[i] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				c[array[i]]++;
			}
			
			for (int i = 1; i < c.length; i++) {
				c[i] += c[i - 1];
			}
			
			Integer[] arrayAux2 = new Integer[rightIndex - leftIndex + 1];
			
			for (int i = rightIndex; i >= leftIndex; i--) {
				c[array[i]]--;
				arrayAux2[c[array[i]]] = array[i];	
			}
			
			for (int i = 0; i < arrayAux2.length; i++) {
				array[leftIndex + i] = arrayAux2[i];
			}
		}
	}

}
