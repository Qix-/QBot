package qix.qbot.rs3.rt6.bot;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;

import qix.qbot.task.Task;
import qix.qbot.task.TaskQueue;


public abstract class QBot extends PollingScript<ClientContext> {
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
