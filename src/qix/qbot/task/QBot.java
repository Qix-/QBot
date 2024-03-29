package qix.qbot.task;


public abstract class QBot {
	private boolean isStarted = false;
	protected TaskQueue queue = new TaskQueue();

	/**
	 * @return The main task that the implementing bot needs to run
	 */
	protected abstract Task getTask();

	public void start() {
		this.isStarted = true;
	}

	public void poll() {
		if (!this.isStarted) {
			return;
		}
		
		if (!this.queue.poll()) {
			this.queue.addTask(this.getTask());
		}
	}

	public void stop() {
		this.isStarted = false;
	}
}
