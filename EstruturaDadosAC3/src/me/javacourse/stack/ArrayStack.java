package me.javacourse.stack;

import me.javacourse.Exceptions.EmptyStackException;
import me.javacourse.Exceptions.FullStackException;

public class ArrayStack<E> implements Stack<E> {

	protected int capacity;
	public static final int CAPACITY = 1000;
	protected E S[];
	protected int top = -1;

	public ArrayStack() {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int cap) {
		capacity = cap;
		S = (E[]) new Object[capacity];
	}

	public int size() {
		return (top + 1);
	}

	public boolean isEmpty() {
		if(this.size() > 0) return false;
		else return true;
	}

	public void push(E element) throws FullStackException {
		if (size() == capacity) throw new FullStackException("Stack is full.");
			S[++top] = element;
	}
	
	public E top() throws EmptyStackException {
		if (isEmpty()) throw new EmptyStackException("Stack is empty.");
			return S[top];
	}

	public E pop() throws EmptyStackException {
		E element;
		if (isEmpty()) throw new EmptyStackException("Stack is empty.");
			element = S[top];
		S[top--] = null;
		return element;
	}

	public String toString() {
		String s;
		s = "(";
		if (size() > 0) s += S[0];
		if (size() > 1) {
			for (int i = 1; i <= size() - 1; i++) {
				s += ", " + S[i];
			}
		}
		return s + ")";
	}

}