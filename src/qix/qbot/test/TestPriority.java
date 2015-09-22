package qix.qbot.test;

import static org.junit.Assert.*;
import static qix.qbot.task.Priority.CRITICAL;
import static qix.qbot.task.Priority.HIGH;
import static qix.qbot.task.Priority.IDLE;
import static qix.qbot.task.Priority.LOW;
import static qix.qbot.task.Priority.LOWEST;
import static qix.qbot.task.Priority.NORMAL;
import static qix.qbot.task.Priority.REQUEST;
import static qix.qbot.task.Priority.URGENT;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import qix.qbot.task.Priority;

public class TestPriority {
	
	private Queue<Priority> queue;

	@Before
	public void setup() {
		queue = new PriorityQueue<>(10, Priority.getComparator());
		this.queue.add(CRITICAL);
		this.queue.add(LOW);
		this.queue.add(LOWEST);
		this.queue.add(LOWEST);
		this.queue.add(LOW);
		this.queue.add(HIGH);
		this.queue.add(REQUEST);
		this.queue.add(CRITICAL);
		this.queue.add(REQUEST);
		this.queue.add(NORMAL);
		this.queue.add(NORMAL);
		this.queue.add(REQUEST);
		this.queue.add(IDLE);
		this.queue.add(LOWEST);
		this.queue.add(CRITICAL);
		this.queue.add(URGENT);
		this.queue.add(CRITICAL);
	}

	@Test
	public void priorityOrdered() {
		Priority[] priorities = new Priority[queue.size()];
		for (int i = 0; !queue.isEmpty(); i++) {
			priorities[i] = queue.poll();
		}

		assertArrayEquals(priorities, new Priority[] {
				CRITICAL,
				CRITICAL,
				CRITICAL,
				CRITICAL,
				URGENT,
				HIGH,
				REQUEST,
				REQUEST,
				REQUEST,
				NORMAL,
				NORMAL,
				LOW,
				LOW,
				LOWEST,
				LOWEST,
				LOWEST,
				IDLE
		});
	}

	@Test
	public void interrupts() {
		assertTrue(NORMAL.isInterruptedBy(HIGH));
		assertFalse(CRITICAL.isInterruptedBy(HIGH));
	}
}
