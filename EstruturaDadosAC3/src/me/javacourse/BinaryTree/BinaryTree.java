package me.javacourse.BinaryTree;

import me.javacourse.Exceptions.BoundaryViolationException;
import me.javacourse.Exceptions.InvalidPositionException;
import me.javacourse.Types.Position;

public interface BinaryTree<T> {
	public Position<T> left(Position<T> v) throws InvalidPositionException, BoundaryViolationException;
	public Position<T> right(Position<T> v) throws InvalidPositionException, BoundaryViolationException;
	public boolean hasLeft(Position<T> v) throws InvalidPositionException;
	public boolean hasRight(Position<T> v) throws InvalidPositionException;
}
