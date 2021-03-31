package tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import me.javacourse.BinaryTree.BTNode;
import me.javacourse.BinaryTree.LinkedBinaryTree;
import me.javacourse.Exceptions.InvalidPositionException;
import me.javacourse.Types.Position;
import me.javacourse.stack.ArrayStack;

class LinkedBinaryTreeTest {

	@Test
	void test() {
		System.out.println("\n--- Exercício 5 . a ---");
		String exp = "((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))";
		LinkedBinaryTree<Character> t = buildExpression(exp);
		
		System.out.println("\n--- Exercício 5 . b ---");
		// binaryPreorder Test
		t.binaryPreorder(t, (BTNode<Character>) t.root());
		System.out.println();
		
		System.out.println("\n--- Exercício 5 . c ---");
		// binaryPostorder Test
		t.binaryPostorder(t, (BTNode<Character>) t.root());
		System.out.println();
		
		System.out.println("\n--- Exercício 5 . d ---");
		// evaluateExpression Test
		System.out.println(evaluateExpression(t, (BTNode<Character>) t.root()));
		
		System.out.println("\n--- Exercício 5 . e ---");
		// inorder Test
		t.inorder(t, (BTNode<Character>) t.root());
		System.out.println();
		
		System.out.println("\n--- Exercício 5 . f ---");
		// makerBTSearch Test
		makeBTSearchTest();
		
		System.out.println("\n--- Exercício 5 . g ---");
		// drawBinaryTree Test
		t.drawBinaryTree(t, t.root());
		
		System.out.println("\n--- Exercício 5 . h ---");
		// eulerTour Test
		t.eulerTour(t, (BTNode<Character>) t.root());
		System.out.println();
		
		System.out.println("\n--- Exercício 5 . i ---");
		// printExpression Test
		t.printExpression(t, t.root());
		
		System.out.println("\n--- Exercício 5 . j ---");
		// countLeftNodes Test
		System.out.println();
		System.out.println(t.countLeftNodes(t, t.root()));
		
		System.out.println("\n--- Exercício 5 . k ---");
		// countRightNodes Test
		System.out.println(t.countRightNodes(t, t.root()));
	}
	
	// Exercício 4
	@Test
	void binaryTreeTest() {
		LinkedBinaryTree<String> lbt = new LinkedBinaryTree<String>();
		lbt.addRoot("Unix");
		assertEquals("Unix", lbt.root().element(), "Unix");
		assertEquals("Linux", lbt.insertLeft(lbt.root(), "Linux").element(), "Linux");
		assertEquals("BSD", lbt.insertRight(lbt.root(), "BSD").element(), "BSD");
		assertEquals(false, lbt.isEmpty(), "Deveria retornar false");
		Assertions.assertThrows(InvalidPositionException.class, () -> { lbt.isInternal(null); });
		Position<String> linux = lbt.left(lbt.root());
		assertEquals("Ubuntu", lbt.insertLeft(linux, "Ubuntu").element(), "Ubuntu");
		assertEquals("Android", lbt.insertRight(linux, "Android").element(), "Android");
		Position<String> ubuntu = lbt.left(linux);
		assertEquals("Ubuntu 13", lbt.insertLeft(ubuntu, "Ubuntu 13").element(), "Ubuntu 13");
		assertEquals("Ubuntu Touch", lbt.insertRight(ubuntu, "Ubuntu Touch").element(), "Ubuntu Touch");
		Assertions.assertThrows(InvalidPositionException.class, () -> { lbt.insertLeft(ubuntu, "teste"); });
		Position<String> bsd = lbt.right(lbt.root());
		assertEquals("FreeBSD", lbt.insertLeft(bsd, "FreeBSD").element(), "FreeBSD");
		assertEquals("NetBSD", lbt.insertRight(bsd, "NetBSD").element(), "NetBSD");
//		lbt.drawBinaryTree(lbt, lbt.root());
	}
	
	// 5. a) buildExpression conforme slide 30.
	public LinkedBinaryTree<Character> buildExpression(String e) {
		ArrayStack<LinkedBinaryTree<Character>> s = new ArrayStack<LinkedBinaryTree<Character>>();
		for (int i = 0; i < e.length(); i++) {
			if (e.charAt(i) != ')' && e.charAt(i) != '(') {
				LinkedBinaryTree<Character> t = new LinkedBinaryTree<Character>();
				t.addRoot(e.charAt(i));
				s.push(t);
			} else if (e.charAt(i) == '(') {
				continue;
			} else {
				LinkedBinaryTree<Character> t2 = s.pop();
				LinkedBinaryTree<Character> t = s.pop();
				LinkedBinaryTree<Character> t1 = s.pop();
				t.attach(t.root(), t1, t2);
				s.push(t);
			}
		}
		return s.pop();
	}
	
	// 5. d) evaluateExpression conforme slide 34 a 42.
	public Double evaluateExpression(LinkedBinaryTree<Character> t, BTNode<Character> v) {
		if (t.isInternal(v)) {
			Character o = v.element();
			double x = evaluateExpression(t, (BTNode<Character>) t.left(v));
			double y = evaluateExpression(t, (BTNode<Character>) t.right(v));
			switch (o) {
			case '+':
				return x + y;
			case '-':
				return x - y;
			case '*':
				return x * y;
			case '/':
				return x / y;
			}
		}
		return Double.parseDouble(v.element().toString());
	}
	
	// 5. f) makerBTSearch e exiba o seu caminhamento inorder conforme slide 45.
	
	public LinkedBinaryTree<Integer> createTreeSearch() {
		LinkedBinaryTree<Integer> t = new LinkedBinaryTree<Integer>();
		t.addRoot(56);
		Position<Integer> v1 = t.insertLeft(t.root(), 31);
		Position<Integer> v2 = t.insertRight(t.root(), 90);
		Position<Integer> v3 = t.insertLeft(v1, 25);
		Position<Integer> v4 = t.insertRight(v1, 42);
		t.insertLeft(v3, 12);
		t.insertLeft(v4, 36);
		Position<Integer> v5 = t.insertLeft(v2, 62);
		t.insertRight(v5, 75);
		return t;
	}
	
	public void makeBTSearchTest() {
		LinkedBinaryTree<Integer> search_tree = createTreeSearch();
		search_tree.makerBTSearch(search_tree, search_tree.root());
	}
	
}
