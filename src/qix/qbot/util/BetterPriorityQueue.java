package qix.qbot.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Does not support duplicates! This class is a little slower than {@link PriorityQueue},
 * so unless you need proper ordering on peek() and array operations, use it instead.
 * 
 * @author qix
 *
 * @param <E>
 */
public class BetterPriorityQueue <E> implements Queue<E>, List<E> {
	
	private static final int REBOUND_AMOUNT = 20;
	
	private int initCapacity;
	private int position = 0;
	private E[] contents = null;
	private Comparator<E> comparator;
	
	public BetterPriorityQueue() {
		this(BetterPriorityQueue.REBOUND_AMOUNT, null);
	}
	
	public BetterPriorityQueue(int capacity) {
		this(capacity, null);
	}
	
	public BetterPriorityQueue(Comparator<E> comparator) {
		this(BetterPriorityQueue.REBOUND_AMOUNT, null);
	}
	
	@SuppressWarnings("unchecked")
	public BetterPriorityQueue(int capacity, Comparator<E> comparator) {
		this.comparator = NullComparators.atEnd(comparator != null
				? comparator
				: (Comparator<E>) Comparator.naturalOrder());
		this.initCapacity = capacity;
		this.rebound();
	}
	
	@SuppressWarnings("unchecked")
	private void rebound() {
		if (this.contents == null) {
			this.contents = (E[]) new Object[this.initCapacity];
			return;
		}

		if (this.position <= this.contents.length) {
			return;
		}

		Object[] old = this.contents;
		this.contents = (E[]) new Object[this.contents.length + BetterPriorityQueue.REBOUND_AMOUNT];
		System.arraycopy(old, 0, this.contents, 0, old.length);
	}
	
	private void sort() {
		Arrays.sort(this.contents, this.comparator);
	}
	
	@Override
	public int size() {
		return this.position;
	}

	@Override
	public boolean isEmpty() {
		return this.position == 0;
	}
	
	@Override
	public boolean contains(Object o) {
		return this.indexOf(o) > -1;
	}

	@Override
	public Iterator<E> iterator() {
		return this.listIterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray() {
		Object[] result = new Object[this.position];
		this.toArray((E[]) result);
		return result;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		System.arraycopy(this.contents, 0, a, 0, Math.min(this.position, a.length));
		return a;
	}

	@Override
	public boolean remove(Object o) {
		int index = this.indexOf(o);
		if (index > -1) {
			this.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (this.indexOf(o) == -1) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean changed = false;

		for (E o : c) {
			if (!this.contains(o)) {
				this.addRaw(o);
				changed = true;
			}
		}

		if (changed) {
			this.sort();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;

		for (Object o : c) {
			int index = this.indexOf(o);
			if (index > -1) {
				this.contents[index] = null;
				changed = true;
			}
		}
		
		if (changed) {
			this.sort();
			return true;
		}

		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;

		for (int i = 0; i < this.position; i++) {
			if (!c.contains(this.contents[i])) {
				this.contents[i] = null;
				changed = true;
			}
		}
		
		if (changed) {
			this.sort();
			return true;
		}

		return false;
	}

	@Override
	public void clear() {
		this.position = 0;
		this.contents = null;
		this.rebound();
	}
	
	protected boolean addRaw(E e) {
		if (this.contains(e)) {
			return false;
		}
		this.rebound();
		this.contents[this.position++] = e;
		return true;
	}

	@Override
	public boolean add(E e) {
		boolean added = this.addRaw(e);
		if (added) {
			this.sort();
		}
		return added;
	}

	@Override
	public boolean offer(E e) {
		return this.add(e);
	}

	@Override
	public E remove() {
		if (this.position == 0) {
			throw new NoSuchElementException();
		}
		
		return this.remove(0);
	}

	@Override
	public E poll() {
		if (this.position == 0) {
			return null;
		}
		
		return this.remove(0);
	}

	@Override
	public E element() {
		if (this.position == 0) {
			throw new NoSuchElementException();
		}
		return this.contents[0];
	}

	@Override
	public E peek() {
		return this.position == 0 ? null : this.contents[0];
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("cannot addAll() with index");
	}

	@Override
	public E get(int index) {
		this.assertBounds(index);
		return this.contents[index];
	}

	@Override
	public E set(int index, E element) {
		this.assertBounds(index);
		
		E previous = this.contents[index];
		this.contents[index] = element;
		
		this.sort();

		return previous;
	}
	
	private void assertBounds(int index) {
		if (index < 0 || index >= this.position) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException("index cannot be specified; use priority");
	}

	@Override
	public E remove(int index) {
		return this.set(index, null);
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			return -1;
		}
		
		for (int i = 0; i < this.position; i++) {
			if (o.equals(this.contents[i])) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		// because we don't allow duplicates.
		return this.indexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return this.listIterator(0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		this.assertBounds(index);
		return new PriorityQueueIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		this.assertBounds(fromIndex);

		if (toIndex > this.position) {
			throw new IndexOutOfBoundsException(Integer.toString(toIndex));
		}

		if (fromIndex > toIndex) {
			throw new IndexOutOfBoundsException(String.format("%d > %d", fromIndex, toIndex));
		}

		List<E> result = new LinkedList<E>();
		for (int i = fromIndex; i < toIndex; i++) {
			result.add(this.contents[i]);
		}

		return result;
	}
	
	private class PriorityQueueIterator extends ArrayIterator<E> {
		PriorityQueueIterator(int start) {
			super(BetterPriorityQueue.this.contents,
					BetterPriorityQueue.this.position,
					start,
					true);
		}
		
		@Override
		public void remove() {
			super.remove();
			BetterPriorityQueue.this.sort();
		}
	}
}
