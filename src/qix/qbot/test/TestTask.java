package qix.qbot.test;

import static org.junit.Assert.assertEquals;

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
		this.queue.addTask(new PingPongTask<Priority>(priorities, Priority.NORMAL), Priority.NORMAL);
		this.queue.addTask(new PingPongTask<Priority>(priorities, Priority.NORMAL, 2), Priority.NORMAL);
		this.queue.addTask(new PingPongTask<Priority>(priorities, Priority.HIGH, 2), Priority.HIGH);
		this.queue.addTask(new PingPongTask<Priority>(priorities, Priority.IDLE, 3), Priority.IDLE);
		
		int count = 0;
		while (this.queue.poll()) {
			++count;
		}
		
		assertEquals(8, count);
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
