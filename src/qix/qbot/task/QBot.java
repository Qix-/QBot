package qix.qbot.task;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;

public abstract class QBot extends PollingScript<ClientContext> {
	private boolean isStarted = false;
	protected TaskQueue queue = new TaskQueue();

	/**
	 * @return The {@link Router} for this bot
	 */
	public abstract Router getRouter();

	@Override
	public void start() {
		this.log.info(String.format("STARTING bot %s for account %s",
				this.getName(), this.ctx.players.local().name()));
		this.isStarted = true;
		super.start();
	}

	@Override
	public void poll() {
		if (!this.isStarted) {
			return;
		}

		this.queue.addTask(this.getRouter().sanityCheck(), Priority.URGENT);
		this.queue.addTask(this.getRouter().sanityCheck(), Priority.URGENT);
	}

	@Override
	public void stop() {
		this.log.info(String.format("STOPPING bot %s for account %s",
				this.getName(), this.ctx.players.local().name()));
		this.isStarted = false;
		super.stop();
	}
}
