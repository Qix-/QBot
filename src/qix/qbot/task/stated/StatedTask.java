package qix.qbot.task.stated;

import qix.qbot.task.Task;
import qix.qbot.task.TaskQueue;


public abstract class StatedTask <E extends Enum<? extends BotFlag>> extends TaskQueue {
	
	private final BotState <E> state;
	
	public StatedTask(BotState<E> state) {
		this(state, null);
	}
	
	public StatedTask(BotState<E> state, String description) {
		super(description);
		this.state = state;
		
		this.addTask(new DelegateTask());
	}
	
	/**
	 * Override this method to perform main task operations.
	 * 
	 * Do NOT override poll() unless you know what you're doing!
	 * @return Whether or not the task is still running.
	 */
	protected abstract boolean pollTask();
	
	protected BotState <E> getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		return String.format("[Task \"%s\"]", this.getDescription());
	}
	
	private class DelegateTask implements Task {
		@Override
		public boolean poll() {
			return StatedTask.this.pollTask();
		}

		@Override
		public String getDescription() {
			return "main";
		}
	}
}
