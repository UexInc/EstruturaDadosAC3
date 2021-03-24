package tests;

// import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import me.javacourse.ArrayDNode.NodePositionList;
import me.javacourse.Tree.LinkedTree;
import me.javacourse.Tree.TreeNode;
import me.javacourse.Tree.TreePosition;
import me.javacourse.Types.DiscNode;
import me.javacourse.Types.Position;
import me.javacourse.Types.PositionList;

class DiskSpaceTest {

	// 3. c) diskSpace conforme o algoritmo do slide 80.
	
	@SuppressWarnings("unused")
	@Test
	void diskSpaceTest() {
		TreePosition<DiscNode> raiz;
		Position<Position<DiscNode>> p, s;
		PositionList<Position<DiscNode>> filhos;
		LinkedTree<DiscNode> T = criarArvoreT();
		diskSpace(T, T.root());
	}

	public int diskSpace(LinkedTree<DiscNode> T, TreePosition<DiscNode> v) {
		int s = v.element().getKbytes();
		for (Position<DiscNode> w : v.getChildren()) {
			s += diskSpace(T, (TreePosition<DiscNode>) w);
		}
		if (T.isInternal(v)) {
			System.out.println(v.getElement().getName() + ": " + s);
		}
		return s;
	}
	
	private TreeNode<DiscNode> criarFilho(TreeNode<DiscNode> p, DiscNode n) {
		PositionList<Position<DiscNode>> filhos;
		TreeNode<DiscNode> aux;
		filhos = p.getChildren();
		aux = new TreeNode<DiscNode>();
		aux.setElement(n);
		aux.setParent(p);
		aux.setChildren(new NodePositionList<Position<DiscNode>>());
		filhos.addLast(aux);
		return aux;
	}
	
	public LinkedTree<DiscNode> criarArvoreT() {
		LinkedTree<DiscNode> T = new LinkedTree<DiscNode>();
		TreeNode<DiscNode> raiz, v1, v2, v3, v4, v5, v6, v7;
		T.addRoot(new DiscNode("/usuário/rt/cursos/", 1));
		raiz = (TreeNode<DiscNode>) T.root();
		raiz.setChildren(new NodePositionList<Position<DiscNode>>());
		v1 = criarFilho(raiz, new DiscNode("cs016/", 2));
		v2 = criarFilho(raiz, new DiscNode("cs252/", 1));
		criarFilho(v1, new DiscNode("notas", 8));
		v3 = criarFilho(v1, new DiscNode("temas/", 1));
		v4 = criarFilho(v1, new DiscNode("programas/", 1));
		criarFilho(v3, new DiscNode("hw1", 3));
		criarFilho(v3, new DiscNode("hw2", 2));
		criarFilho(v3, new DiscNode("hw3", 4));
		criarFilho(v4, new DiscNode("pr1", 57));
		criarFilho(v4, new DiscNode("pr2", 97));
		criarFilho(v4, new DiscNode("pr3", 74));
		v5 = criarFilho(v2, new DiscNode("projetos/", 1));
		criarFilho(v2, new DiscNode("notas/", 3));
		v6 = criarFilho(v5, new DiscNode("trabalhos/", 1));
		v7 = criarFilho(v5, new DiscNode("demos/", 1));
		criarFilho(v6, new DiscNode("compre baixo", 26));
		criarFilho(v6, new DiscNode("venda alto", 55));
		criarFilho(v7, new DiscNode("mercado", 4786));
		return T;
	}


}
