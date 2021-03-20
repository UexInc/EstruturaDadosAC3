package me.javacourse.Tree;

import java.util.Iterator;

import me.javacourse.Exceptions.BoundaryViolationException;
import me.javacourse.Exceptions.EmptyTreeException;
import me.javacourse.Exceptions.InvalidPositionException;
import me.javacourse.Types.Position;

public interface Tree<T> extends Iterable<T> {
	
	public int size();
	public boolean isEmpty();
	public Iterator<T> iterator();
	public Iterable<Position<T>> positions();
	public T replace(Position<T> v, T e) throws InvalidPositionException;
	public TreePosition<T> root() throws EmptyTreeException;
	public TreePosition<T> parent(Position<T> v) throws InvalidPositionException, BoundaryViolationException;
	public Iterable<Position<T>> children(Position<T> v) throws InvalidPositionException;
	public boolean isInternal(Position<T> v) throws InvalidPositionException;
	public boolean isExternal(Position<T> v) throws InvalidPositionException;
	public boolean isRoot(Position<T> v) throws InvalidPositionException;

}