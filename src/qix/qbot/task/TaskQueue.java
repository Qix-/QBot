package qix.qbot.task;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskQueue implements Task {
	private final PriorityQueue<TaskExecution> queue;
	private final String description;
	private TaskExecution currentTask = null;
	
	public TaskQueue() {
		this("main task queue");
	}
	
	public TaskQueue(String description) {
		this.queue = new PriorityQueue<>(10, TaskExecution.comparator);
		this.description = description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public boolean poll() {
		if (currentTask == null) {
			return false;
		}
		
		boolean result = currentTask.task.poll();
		if (!result) {
			this.currentTask = null;
			this.updateTask();
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return "[TaskQueue \"" + this.getDescription() + "\"]";
	}
	
	/**
	 * Adds a {@link Task} at the <code>NORMAL</code> {@link Priority}.
	 * @param task The {@link Task} to add
	 */
	public void addTask(Task task) {
		this.addTask(task, Priority.NORMAL);
	}
	
	/**
	 * Adds a {@link Task} at a given {@link Priority}.
	 * @param task The {@link Task} to add
	 * @param priority The {@link Priority} at which to run the task
	 */
	public void addTask(Task task, Priority priority) {
		this.queue.add(new TaskExecution(task, priority));
		this.updateTask();
	}
	
	protected void updateTask() {
		if (this.currentTask == null) {
			this.currentTask = this.queue.poll();
		} else if (currentTask.priority.isInterruptedBy(this.queue.peek().priority)) {
			TaskExecution next = this.queue.poll();
			this.queue.add(this.currentTask);
			this.currentTask = next;
			// TODO log interruption
		}
	}
	
	public Task getCurrentTask() {
		return this.currentTask.task;
	}
	
	static class TaskExecution {
		private final Priority priority;
		private final Task task;
		
		public TaskExecution(Task task, Priority priority) {
			this.task = task;
			this.priority = priority;
		}
		
		public Task getTask() {
			return this.task;
		}
		
		public Priority getPriority() {
			return this.priority;
		}
		
		static final Comparator<TaskExecution> comparator = new Comparator<TaskExecution>() {
			private final Comparator<Priority> priorityComparator = Priority.getComparator();

			@Override
			public int compare(TaskExecution o1, TaskExecution o2) {
				return this.priorityComparator.compare(o2.priority, o1.priority);
			}
		};
	}
}
