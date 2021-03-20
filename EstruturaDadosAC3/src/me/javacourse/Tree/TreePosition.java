package me.javacourse.Tree;

import me.javacourse.Types.Position;
import me.javacourse.Types.PositionList;

public interface TreePosition<T> extends Position<T> {

	public void setElement(T o);
	public T getElement();
	public PositionList<Position<T>> getChildren();
	public void setChildren(PositionList<Position<T>> c);
	public TreePosition<T> getParent();
	public void setParent(TreePosition<T> v);

}
