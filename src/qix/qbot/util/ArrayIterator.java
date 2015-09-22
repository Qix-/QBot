package qix.qbot.util;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayIterator <E> implements Iterator<E>, ListIterator<E> {
	
	private final E[] array;
	private final int length;
	private final boolean nullRemove;
	private int position;

	public ArrayIterator(E[] array) {
		this(array, array.length);
	}
	
	public ArrayIterator(E[] array, int length) {
		this(array, length, 0);
	}
	
	public ArrayIterator(E[] array, boolean nullRemove) {
		this(array, array.length, nullRemove);
	}
	
	public ArrayIterator(E[] array, int length, boolean nullRemove) {
		this(array, length, 0, nullRemove);
	}
	
	public ArrayIterator(E[] array, int length, int position) {
		this(array, length, position, false);
	}
	
	public ArrayIterator(E[] array, int length, int position, boolean nullRemove) {
		this.array = array;
		this.length = length;
		this.nullRemove = nullRemove;
		this.position = position;
	}

	@Override
	public boolean hasNext() {
		return this.position < this.length;
	}

	@Override
	public E next() {
		return this.array[this.position++];
	}

	@Override
	public boolean hasPrevious() {
		return this.position > 0;
	}

	@Override
	public E previous() {
		if (this.position == 0) {
			throw new NoSuchElementException();
		}
		
		return this.array[--this.position];
	}

	@Override
	public int nextIndex() {
		return this.position;
	}

	@Override
	public int previousIndex() {
		return this.position - 1;
	}

	@Override
	public void remove() {
		if (!this.nullRemove) {
			throw new UnsupportedOperationException();
		}
		
		if (this.position >= this.length) {
			throw new IllegalStateException();
		}
		
		this.array[this.position] = null;
	}

	@Override
	public void set(E e) {
		this.array[this.position] = e;
	}

	@Override
	public void add(E e) {
		throw new UnsupportedOperationException("cannot add to array");
	}

}
