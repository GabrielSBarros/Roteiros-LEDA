package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0 && leftIndex <= rightIndex) {
			int maior = array[leftIndex];
			int menor = array[leftIndex];
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if(array[i] > maior) {
					maior = array[i];
				}
				if(array[i] < menor) {
					menor = array[i];
				}	
			}
			
			int complemento = -menor;
			Integer[] c = new Integer[maior - menor + 1];
			for (int i = 0; i < c.length; i++) {
				c[i] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				c[array[i] + complemento]++;
			}
			
			for (int i = 1; i < c.length; i++) {
				c[i] += c[i - 1];
			}
			
			Integer[] arrayAux2 = new Integer[rightIndex - leftIndex + 1];
			
			for (int i = rightIndex; i >= leftIndex; i--) {
				c[array[i] + complemento]--;
				arrayAux2[c[array[i] + complemento]] = array[i];	
			}
			
			for (int i = 0; i < arrayAux2.length; i++) {
				array[leftIndex + i] = arrayAux2[i];
			}		
		}
	}

}
