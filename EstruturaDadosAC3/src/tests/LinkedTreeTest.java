package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import me.javacourse.ArrayDNode.NodePositionList;
import me.javacourse.Tree.LinkedTree;
import me.javacourse.Tree.TreeNode;
import me.javacourse.Tree.TreePosition;
import me.javacourse.Types.Position;
import me.javacourse.Types.PositionList;

class LinkedTreeTest {
	
	// 2. Implementação e teste da Árvore Genérica conforme os slides de 23 a 33.
	
	/* 3. Incrementar a implementação e teste de LinkedTree<E> com os seguinte métodos:
		a) parentheticRepresentation conforme o algoritmo do slide 55.
		b) Com base no algoritmo posorder (slide 57) crie o método toStringPostorder (imprime os valores dos nodes visitados).
		(c Feita em DiskSpaceTest)
		d) depth conforme o algoritmo do slide 84.
		e) height1 conforme o algoritmo do slide 90.
		f) height2 conforme o algoritmo do slide 103. */
	
	@Test

	void linkedTreeTest() {
		TreePosition<String> raiz;
		Position<Position<String>> p, s, q, t;
		PositionList<Position<String>> filhos;
		LinkedTree<String> T = criarArvoreT();
		assertFalse(T.isEmpty());
		assertEquals(3, T.height1(T), "Altura da Árvore T");
		assertEquals(3, T.height2(T, T.root()), "Altura da Árvore T");
		assertEquals("(C, C++, Rust, Java, Kotlin, C#, Python, Ruby, Swift, Go)",
				T.toString(), "Pré-ordem da Árvore T ");
		raiz = T.root();
		filhos = raiz.getChildren();
		p = filhos.first();
		assertEquals("C++", p.element().element(), "C++");
		assertTrue(T.isInternal(p.element()));
		assertEquals(raiz, T.parent(p.element()), "Deve ser a raiz");
		s = filhos.next(p);
		assertEquals("Python", s.element().element(), "Python");
		q = ((PositionList<Position<String>>) T.children(s.element())).last();
		assertEquals(1, T.depth(T, s.element()), "");
		assertTrue(T.isExternal(q.element()));
		t = ((PositionList<Position<String>>) T.children(s.element())).first();
		T.replace(((PositionList<Position<String>>) T.children(t.element())).first().element(), 
				"R");
		assertEquals("(C, C++, Rust, Java, Kotlin, C#, Python, Ruby, R, Go)",
				T.toString(), "Pré-ordem da Árvore T ");
		assertTrue(T.isRoot(raiz));
		T.swapElements(((PositionList<Position<String>>) T.children(p.element())).first().element(), 
				((PositionList<Position<String>>) T.children(p.element())).last().element());
		assertEquals("(C, C++, C#, Java, Kotlin, Rust, Python, Ruby, R, Go)",
				T.toString(), "Pré-ordem da Árvore T ");
		System.out.println(T.parentheticRepresentation(T, T.root()));
		System.out.println(T.toStringPostorder(T, T.root()));
	}

	private TreeNode<String> criarFilho(TreeNode<String> p, String n) {
		PositionList<Position<String>> filhos;
		TreeNode<String> aux;
		filhos = p.getChildren();
		aux = new TreeNode<String>();
		aux.setElement(n);
		aux.setParent(p);
		aux.setChildren(new NodePositionList<Position<String>>());
		filhos.addLast(aux);
		return aux;
	}

	public LinkedTree<String> criarArvoreT() {
		LinkedTree<String> T = new LinkedTree<String>();
		TreeNode<String> raiz, v, m, i, u;
		T.addRoot("C");
		raiz = (TreeNode<String>) T.root();
		raiz.setChildren(new NodePositionList<Position<String>>());
		v = criarFilho(raiz, "C++");
		m = criarFilho(raiz, "Python");
		criarFilho(v, "Rust");
		i = criarFilho(v, "Java");
		criarFilho(v, "C#");
		u = criarFilho(m, "Ruby");
		criarFilho(m, "Go");
		criarFilho(i, "Kotlin");
		criarFilho(u, "Swift");
		return T;
	}

}
