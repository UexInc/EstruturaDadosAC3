package me.javacourse.Tree;

import java.util.Iterator;

import me.javacourse.ArrayDNode.NodePositionList;
import me.javacourse.Exceptions.BoundaryViolationException;
import me.javacourse.Exceptions.EmptyTreeException;
import me.javacourse.Exceptions.InvalidPositionException;
import me.javacourse.Exceptions.NonEmptyTreeException;
import me.javacourse.Types.Position;
import me.javacourse.Types.PositionList;

public class LinkedTree<T> implements Tree<T> {

	protected TreePosition<T> root;
	protected int size;

	public LinkedTree() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public boolean isInternal(Position<T> v) throws InvalidPositionException {
		return !isExternal(v);
	}

	public boolean isExternal(Position<T> v) throws InvalidPositionException {
		TreePosition<T> vv = checkPosition(v);
		return (vv.getChildren() == null) || vv.getChildren().isEmpty();
	}

	public boolean isRoot(Position<T> v) throws InvalidPositionException {
		checkPosition(v);
		return (v == root());
	}

	public TreePosition<T> root() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException("The tree is empty");
		return root;
	}

	public TreePosition<T> parent(Position<T> v) throws InvalidPositionException, BoundaryViolationException {
		TreePosition<T> vv = checkPosition(v);
		TreePosition<T> parentPos = vv.getParent();
		if (parentPos == null)
			throw new BoundaryViolationException("No parent");
		return parentPos;
	}

	public Iterable<Position<T>> children(Position<T> v) throws InvalidPositionException {
		TreePosition<T> vv = checkPosition(v);
		return vv.getChildren();
	}

	public Iterable<Position<T>> positions() {
		PositionList<Position<T>> positions = new NodePositionList<Position<T>>();
		if (size != 0)
			preorderPositions(root(), positions);
		return positions;
	}

	public Iterator<T> iterator() {
		Iterable<Position<T>> positions = positions();
		PositionList<T> elements = new NodePositionList<T>();
		for (Position<T> pos : positions)
			elements.addLast(pos.element());
		return elements.iterator();
	}

	public T replace(Position<T> v, T o) throws InvalidPositionException {
		TreePosition<T> vv = checkPosition(v);
		T temp = v.element();
		vv.setElement(o);
		return temp;
	}

	public TreePosition<T> addRoot(T e) throws NonEmptyTreeException {
		if (!isEmpty())
			throw new NonEmptyTreeException("Tree already has a root");
		size = 1;
		root = createNode(e, null, null);
		return root;
	}

	public void swapElements(Position<T> v, Position<T> w) throws InvalidPositionException {
		TreePosition<T> vv = checkPosition(v);
		TreePosition<T> ww = checkPosition(w);
		T temp = w.element();
		ww.setElement(v.element());
		vv.setElement(temp);
	}

	protected TreePosition<T> checkPosition(Position<T> v) throws InvalidPositionException {
		if (v == null || !(v instanceof TreePosition))
			throw new InvalidPositionException("The position is invalid");
		return (TreePosition<T>) v;
	}

	protected TreePosition<T> createNode(T element, TreePosition<T> parent, PositionList<Position<T>> children) {
		return new TreeNode<T>(element, parent, children);
	}

	protected void preorderPositions(Position<T> v, PositionList<Position<T>> pos) throws InvalidPositionException {
		pos.addLast(v);
		for (Position<T> w : children(v))
			preorderPositions(w, pos);
	}

	// depth
	public int depth(Tree<T> T, Position<T> v) {
		if (T.isRoot(v))
			return 0;
		else {
			Position<T> w = ((TreeNode<T>) v).getParent();
			return 1 + depth(T, w);
		}
	}

	// height1

	public int Max(int x, int y) {
		return x > y ? x : y;
	}

	public int height1(Tree<T> T) {
		int h = 0;
		for (Position<T> v : positions()) {
			if (isExternal(v))
				h = Max(h, depth(T, v));
		}
		return h;
	}

	// height2
	public int height2(Tree<T> T, Position<T> v) {
		if (isExternal(v))
			return 0;
		else {
			int h = 0;
			for (Position<T> w : children(v))
				h = Max(h, height2(T, w));
			return 1 + h;
		}
	}

	// TODO parentheticRepresentation
	public String parentheticRepresentation(Tree<T> T, Position<T> v) {
		String s = v.element().toString();
		String tabs = "";
		int contTabs = depth(T, v);
		if (T.isInternal(v)) {
			Boolean firstTime = true;
			for (Position<T> w : T.children(v)) {
				if (firstTime) {
					for (int i = 0; i <= contTabs; i++) {
						tabs += "\t";
					}
					s += " (\n" + tabs + parentheticRepresentation(T, w);
					firstTime = false;
				} else {
					s += ",\n" + tabs + parentheticRepresentation(T, w);
				}
			}
			if(!firstTime)
				s += "\n" + tabs.substring(0, tabs.length()-1) + ")";
		}
		return s;
	}

	// TODO toStringPostorder
	public String toStringPostorder(Tree<T> T, Position<T> v) {
		String s = "";
		for (Position<T> w : children(v))
			s += toStringPostorder(T, w);
		Position<T> temp = checkPosition(v);
		s += temp.element().toString() + "\n";
		return s;
	}

	// -
	public String toString() {
		return toString(this);
	}

	public static <T> String toString(LinkedTree<T> T) {
		String s = "";
		for (T i : T) {
			s += ", " + i;
		}
		s = (s.length() == 0 ? s : s.substring(2));
		return "(" + s + ")";
	}
}