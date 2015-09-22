package qix.qbot.task.stated;

import qix.qbot.task.TaskQueue;


public abstract class StatedTask <E extends Enum<? extends BotFlag>> extends TaskQueue {
	
	private final BotState <E> state;
	
	public StatedTask(BotState <E> state) {
		this.state = state;
	}
	
	protected BotState <E> getState() {
		return this.state;
	}
	
	@Override
	public boolean poll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return String.format("[Task \"%s\"]", this.getDescription());
	}
}
