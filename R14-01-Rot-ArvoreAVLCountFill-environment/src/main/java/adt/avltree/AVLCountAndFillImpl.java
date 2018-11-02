package adt.avltree;

import java.util.ArrayList;
import java.util.Collections;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			if (balance > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
					leftRotation((BSTNode<T>) node.getLeft());
					RLcounter++;
				} else {
					LLcounter++;
				}
				rightRotation(node);
			} else {
				if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
					rightRotation((BSTNode<T>) node.getRight());
					LRcounter++;
				} else {
					RRcounter++;
				}
				leftRotation(node);

			}
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		ArrayList<T> aux = new ArrayList<>();
		while (!isEmpty()) {
			aux.add(removeWithoutRebalance());
		}
		for (int i = 0; i < array.length; i++) {
			aux.add(array[i]);
		}
		Collections.sort(aux);
		if(aux.size() > 0) {
			insertAll(elementosMedios(aux));
		}
	}

	private void insertAll(ArrayList<T> array) {
		for (int i = 0; i < array.size(); i++) {
			insert(array.get(i));
		}
	}

	/**
	 * Retorno o elemento no meio do array, entao divide o array em 2 e recursivamente adiciona ao retorno o elemento no meio deles.
	 * 
	 * @param array
	 * @return
	 */
	private ArrayList<T> elementosMedios(ArrayList<T> array) {
		ArrayList<T> retorno = new ArrayList<>();
		if (array.size() > 0) {
			int indiceMedio = (array.size() - 1) / 2;
			retorno.add(array.get(indiceMedio));
			ArrayList<T> array1 = elementosMedios(subArrayList(array, 0, indiceMedio - 1));
			ArrayList<T> array2 = elementosMedios(subArrayList(array, indiceMedio + 1, array.size() - 1));
			retorno.addAll(merge(array1, array2));
		}
		return retorno;

	}

	private ArrayList<T> subArrayList(ArrayList<T> array, int fromIndex, int toIndex) {
		ArrayList<T> retorno = new ArrayList<>();
		for (int i = fromIndex; i <= toIndex; i++) {
			retorno.add(array.get(i));
		}
		return retorno;
	}


	private ArrayList<T> merge(ArrayList<T> array1, ArrayList<T> array2) {
		ArrayList<T> retorno = new ArrayList<>();
		int size;
		if (array1.size() < array2.size()) {
			size = array1.size();
		} else {
			size = array2.size();
		}
		
		for (int i = 0; i < size; i++) {
			retorno.add(array1.remove(0));
			retorno.add(array2.remove(0));
		}

		for (int i = 0; i < array1.size(); i++) {
			retorno.add(array1.remove(0));
		}
		for (int i = 0; i < array2.size(); i++) {
			retorno.add(array2.remove(0));
		}
		return retorno;

	}

	private T removeWithoutRebalance() {
		BSTNode<T> aux = getRoot();
		while (!aux.isLeaf()) {
			if (calculateBalance(aux) >= 0) {
				aux = (BSTNode<T>) aux.getLeft();
			} else {
				aux = (BSTNode<T>) aux.getRight();
			}
		}
		T nodeData = aux.getData();
		remove(nodeData);
		return nodeData;
	}

}
