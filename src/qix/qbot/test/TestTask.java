package qix.qbot.test;

import static org.junit.Assert.assertEquals;
import static qix.qbot.task.Priority.CRITICAL;
import static qix.qbot.task.Priority.HIGH;
import static qix.qbot.task.Priority.IDLE;
import static qix.qbot.task.Priority.LOW;
import static qix.qbot.task.Priority.NORMAL;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import qix.qbot.task.Priority;
import qix.qbot.task.Task;
import qix.qbot.task.TaskQueue;

public class TestTask {
	
	private TaskQueue queue;

	@Before
	public void setUp() throws Exception {
		queue = new TaskQueue("some queue");
	}

	@Test
	public void string() {
		assertEquals("[TaskQueue \"some queue\"]", this.queue.toString());
	}
	
	@Test
	public void simpleTasks() {
		List<Priority> priorities = new LinkedList<>();
		this.queue.addTask(new PingPongTask<Priority>(priorities, NORMAL), NORMAL);
		this.queue.addTask(new PingPongTask<Priority>(priorities, NORMAL, 2), NORMAL);
		this.queue.addTask(new PingPongTask<Priority>(priorities, HIGH, 2), HIGH);
		this.queue.addTask(new PingPongTask<Priority>(priorities, IDLE, 3), IDLE);
		
		int count = 0;
		while (this.queue.poll()) {
			++count;
		}
		
		assertEquals(8, count);
	}
	
	@Test
	public void interruptTask() {
		List<Priority> priorities = new LinkedList<>();
		this.queue.addTask(new PingPongTask<Priority>(priorities, NORMAL, 2), NORMAL);
		this.queue.addTask(new PingPongTask<Priority>(priorities, LOW, 2), LOW);
		this.queue.poll();
		
		this.queue.addTask(new PingPongTask<Priority>(priorities, HIGH, 2), HIGH);
		this.queue.poll();
		
		this.queue.addTask(new PingPongTask<Priority>(priorities, CRITICAL, 1), CRITICAL);

		while (this.queue.poll()) {}
		
		TestUtils.assertCollectionEquals(priorities, NORMAL, HIGH, CRITICAL, HIGH, NORMAL, LOW, LOW);
	}

	static class PingPongTask <E> implements Task {
		private final E pingpong;
		private final List<E> list;
		private int count;
		
		public PingPongTask(List<E> list, E pingpong) {
			this(list, pingpong, 1);
		}

		public PingPongTask(List<E> list, E pingpong, int count) {
			this.pingpong = pingpong;
			this.count = count;
			this.list = list;
		}

		@Override
		public boolean poll() {
			this.list.add(this.pingpong);
			return --this.count > 0;
		}

		@Override
		public String getDescription() {
			return this.pingpong.toString();
		}
	}
}
