package me.javacourse.Tree;

import me.javacourse.Types.Position;
import me.javacourse.Types.PositionList;

public class TreeNode<T> implements TreePosition<T> {

	private T element;
	private TreePosition<T> parent;
	private PositionList<Position<T>> children;

	public TreeNode() {}

	public TreeNode(T element, TreePosition<T> parent, PositionList<Position<T>> children) {
		this.setElement(element);
		this.setParent(parent);
		this.setChildren(children);
	}

	public T element() {
		return element;
	}

	public void setElement(T o) {
		element = o;
	}

	public T getElement() {
		return element;
	}

	public PositionList<Position<T>> getChildren() {
		return children;
	}

	public void setChildren(PositionList<Position<T>> c) {
		children = c;
	}

	public TreePosition<T> getParent() {
		return parent;
	}

	public void setParent(TreePosition<T> v) {
		parent = v;
	}

}
