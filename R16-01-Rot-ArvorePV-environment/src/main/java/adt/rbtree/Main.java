package adt.rbtree;

import java.util.Arrays;

public class Main {
	
	public static void print(RBTreeImpl<Integer> tree) {
		System.out.println(Arrays.toString(tree.rbPreOrder()));
	}

	public static void main(String[] args) {
		RBTreeImpl<Integer> tree = new RBTreeImpl<>();
		tree.insert(11);
		print(tree);
		//tree.insert(2);
		print(tree);
		tree.insert(14);
		print(tree);
		//tree.insert(1);
		//print(tree);
		tree.insert(15);
		print(tree);
		tree.insert(5);
		print(tree);
		tree.insert(7);
		print(tree);
		
		
		System.out.println(Arrays.toString(tree.rbPreOrder()));
		System.out.println("VerifyBlackHeight " + tree.verifyBlackHeight());
		
	}
}
