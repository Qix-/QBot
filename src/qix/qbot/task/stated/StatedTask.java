package qix.qbot.task.stated;

import qix.qbot.task.Task;
import qix.qbot.task.TaskQueue;


public abstract class StatedTask <E extends Enum<? extends BotFlag>> extends TaskQueue {
	
	private final BotState<E> state;
	
	public StatedTask(BotState<E> state) {
		this(state, null);
	}
	
	public StatedTask(BotState<E> state, String description) {
		super(description);
		this.state = state;
		this.addTask(new DelegateTask());
	}
	
	protected abstract boolean pollTask();
	
	@Override
	public final boolean poll() {
		return super.poll();
	}
	
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
