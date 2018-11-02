package adt.btree;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		BTreeImpl<Integer> tree = new BTreeImpl<>(4);
		tree.insert(48);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(18);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(12);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(9);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(6);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(43);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(35);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(47);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(4);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		System.out.println(1);	
		tree.insert(1);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		System.out.println(37);
		tree.insert(37);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		System.out.println(49);
		tree.insert(49);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		
		//segunda bateria de testes
		System.out.println("\n\n\n\n\n\n\n\n\n");
		tree.insert(14);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(7);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(89);
		System.out.println(89);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(11);
		System.out.println(11);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(92);
		System.out.println(92);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(88);
		System.out.println(88);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(32);
		System.out.println(32);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(82);
		System.out.println(82);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(83);
		System.out.println(83);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(58);
		System.out.println(58);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(81);
		System.out.println(81);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		tree.insert(56);
		System.out.println(56);
		System.out.println(Arrays.toString(tree.depthLeftOrder()));
		
		
	}
}
