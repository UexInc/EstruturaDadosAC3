package tests;


import org.junit.jupiter.api.Test;

import me.javacourse.BinaryTree.BTNode;
import me.javacourse.BinaryTree.LinkedBinaryTree;
import me.javacourse.Types.Position;
import me.javacourse.stack.ArrayStack;

class LinkedBinaryTreeTest {

	@Test
	void test() {
		String exp = "((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))";
		LinkedBinaryTree<Character> t = buildExpression(exp);
		// binaryPreorder Test
		t.binaryPreorder(t, (BTNode<Character>) t.root());
		System.out.println();
		// binaryPostorder Test
		t.binaryPostorder(t, (BTNode<Character>) t.root());
		System.out.println();
		// evaluateExpression Test
		System.out.println(evaluateExpression(t, (BTNode<Character>) t.root()));
		// inorder Test
		t.inorder(t, (BTNode<Character>) t.root());
		System.out.println();
		// makerBTSearch Test
		makeBTSearchTest();
		// eulerTour Test
		t.eulerTour(t, (BTNode<Character>) t.root());
		System.out.println();
		// printExpression Test
		t.printExpression(t, t.root());
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
	
	/* TODO:
	 * 5. g) Método que desenhe a árvore binária de expressão conforme slide 47.
	 * 5. j) Método para contar os nodos esquerdos e externos de uma árvore binária.
	 * 5. k) Método para contar os nodos direitos e externos de uma árvore binária.
	 */
	
}
