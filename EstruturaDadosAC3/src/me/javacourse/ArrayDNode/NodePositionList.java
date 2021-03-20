package me.javacourse.ArrayDNode;

import java.util.Iterator;

import me.javacourse.Exceptions.BoundaryViolationException;
import me.javacourse.Exceptions.EmptyListException;
import me.javacourse.Exceptions.InvalidPositionException;
import me.javacourse.Types.Position;
import me.javacourse.Types.PositionList;

public class NodePositionList<T> implements PositionList<T> {

	protected int numElts;

	protected DNode<T> header, trailer;

	public NodePositionList() {
		numElts = 0;
		header = new DNode<T>(null, null, null);
		trailer = new DNode<T>(header, null, null);
		header.setNext(trailer);
	}

	protected DNode<T> checkPosition(Position<T> p) throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException("Null position passed to NodeList");
		if (p == header)
			throw new InvalidPositionException("The header node is not a valid position");
		if (p == trailer)
			throw new InvalidPositionException("The trailer node is not a valid position");
		try {
			DNode<T> temp = (DNode<T>) p;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("Position does not belong to a valid NodeList");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Position is of wrong type for this list");
		}
	}

	public int size() {
		return numElts;
	}

	public boolean isEmpty() {
		return (numElts == 0);
	}

	public Position<T> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("List is empty");
		return header.getNext();
	}

	public Position<T> prev(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<T> v = checkPosition(p);
		DNode<T> prev = v.getPrev();
		if (prev == header)
			throw new BoundaryViolationException("Cannot advance past the beginning of the list");
		return prev;
	}

	public void addBefore(Position<T> p, T element) throws InvalidPositionException {
		DNode<T> v = checkPosition(p);
		numElts++;
		DNode<T> newNode = new DNode<T>(v.getPrev(), v, element);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
	}

	public void addFirst(T element) {
		numElts++;
		DNode<T> newNode = new DNode<T>(header, header.getNext(), element);
		header.getNext().setPrev(newNode);
		header.setNext(newNode);
	}

	public T remove(Position<T> p) throws InvalidPositionException {
		DNode<T> v = checkPosition(p);
		numElts--;
		DNode<T> vPrev = v.getPrev();
		DNode<T> vNext = v.getNext();
		vPrev.setNext(vNext);
		vNext.setPrev(vPrev);
		T vElem = v.element();
		v.setNext(null);
		v.setPrev(null);
		return vElem;
	}

	public T set(Position<T> p, T element) throws InvalidPositionException {
		DNode<T> v = checkPosition(p);
		T oldElt = v.element();
		v.setElement(element);
		return oldElt;
	}

	public Position<T> last() {
		if (isEmpty())
			throw new EmptyListException("List is empty");
		return trailer.getPrev();
	}

	public Position<T> next(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
		DNode<T> v = checkPosition(p);
		DNode<T> next = v.getNext();
		if (next == trailer)
			throw new BoundaryViolationException("Cannot advance past the finaling of the list");
		return next;
	}

	public void addLast(T e) {
		numElts++;
		DNode<T> newNode = new DNode<T>(trailer.getPrev(), trailer, e);
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
	}

	public void addAfter(Position<T> p, T e) throws InvalidPositionException {
		DNode<T> v = checkPosition(p);
		numElts++;
		DNode<T> newNode = new DNode<T>(v, v.getNext(), e);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
	}
	
	public void makeFirst(Position<T> p) {
		DNode<T> temp = checkPosition(p);
		this.addFirst(temp.element());
		this.remove(p);
	}
	
	public void reverseNodePositionList() {
		DNode<T> xPos = (DNode<T>) this.first();
		DNode<T> yPos = (DNode<T>) this.last();
		int c = 0;
		int comparator = (this.size() % 2 == 0) ? this.size() / 2 + 1 : this.size() / 2;
		while (comparator != c) {
			T temp = this.set(xPos, yPos.element());
			this.set(yPos, temp);
			xPos = xPos.getNext();
			yPos = yPos.getPrev();
			c++;
		}
	}

	private static <T> String toString(PositionList<T> l) {
		String s = "";
		for (T i : l) {
			s += ", " + i;
		}
		s = (s.length() == 0 ? s : s.substring(2));
		return "(" + s + ")";
	}

	public Iterator<T> iterator() {
		return new ElementIterator<T>(this);
	}

	public String toString() {
		return toString(this);
	}
}
