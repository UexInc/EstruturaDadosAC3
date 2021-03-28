package me.javacourse.BinaryTree;

import me.javacourse.Types.Position;

public interface BTPosition<T> extends Position<T> {
	public T element();
	public void setElement(T o);
	public BTPosition<T> getLeft();
	public void setLeft(BTPosition<T> v);
	public BTPosition<T> getRight();
	public void setRight(BTPosition<T> v);
	public BTPosition<T> getParent();
	public void setParent(BTPosition<T> v);
}
