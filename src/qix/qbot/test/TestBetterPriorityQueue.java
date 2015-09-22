package qix.qbot.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import qix.qbot.task.Priority;
import qix.qbot.util.BetterPriorityQueue;

public class TestBetterPriorityQueue {
	
	private BetterPriorityQueue<Integer> queue;

	@Before
	public void setUp() throws Exception {
		queue = new BetterPriorityQueue<>();
	}
	
	private void assertSame(Integer... array) {
		assertArrayEquals(array, this.queue.toArray());
	}

	@Test
	public void testAdd() {
		this.queue.add(1234);
		this.assertSame(1234);
	}

	@Test
	public void testAddMultiple() {
		this.queue.add(1);
		this.queue.add(15);
		this.queue.add(10);
		this.queue.add(25);
		this.assertSame(1, 10, 15, 25);
	}
	
	@Test
	public void testPoll() {
		this.queue.add(15);
		this.queue.add(10);
		this.queue.add(100);
		this.queue.add(2);
		this.assertSame(2, 10, 15, 100);
		assertEquals(2, (int) this.queue.poll());
		assertEquals(10, (int) this.queue.poll());
		assertEquals(15, (int) this.queue.poll());
		assertEquals(100, (int) this.queue.poll());
		assertEquals(null, this.queue.poll());
	}
	
	@Test
	public void testPeek() {
		this.queue.add(10);
		this.queue.add(2);
		this.queue.add(100);
		
		assertEquals(2, (int) this.queue.peek());
		assertEquals(2, (int) this.queue.peek());
		assertEquals(2, (int) this.queue.poll());
		assertEquals(10, (int) this.queue.peek());
		assertEquals(10, (int) this.queue.peek());
		assertEquals(10, (int) this.queue.poll());
	}
	
	@Test
	public void iterator() {
		Collections.addAll(this.queue, 20, 20, 20, 15, 10, 16);
		List<Integer> result = new LinkedList<>();
		for (Integer i : this.queue) {
			result.add(i);
		}
		assertArrayEquals(result.toArray(), new Integer[]{ 10, 15, 16, 20 });
	}
	
	@Test
	public void comparator() {
		Queue<Priority> priorities = new BetterPriorityQueue<Priority>(Priority.getComparator());
		
		Collections.addAll(priorities, Priority.HIGH, Priority.NORMAL, Priority.CRITICAL,
				Priority.NORMAL, Priority.LOW);
		
		TestUtils.assertCollectionEquals(priorities, Priority.CRITICAL, Priority.HIGH,
				Priority.NORMAL, Priority.LOW);
	}
}