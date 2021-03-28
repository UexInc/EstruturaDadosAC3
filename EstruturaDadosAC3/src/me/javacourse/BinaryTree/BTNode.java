package me.javacourse.BinaryTree;

public class BTNode<T> implements BTPosition<T> {

	private T element;

	private BTPosition<T> left, right, parent;

	public BTNode(T element, BTPosition<T> parent, BTPosition<T> left, BTPosition<T> right) {
		setElement(element);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}

	public T element() {
		return element;
	}

	public void setElement(T o) {
		element = o;
	}

	public BTPosition<T> getLeft() {
		return left;
	}

	public void setLeft(BTPosition<T> v) {
		left = v;
	}

	public BTPosition<T> getRight() {
		return right;
	}

	public void setRight(BTPosition<T> v) {
		right = v;
	}

	public BTPosition<T> getParent() {
		return parent;
	}

	public void setParent(BTPosition<T> v) {
		parent = v;
	}

}
