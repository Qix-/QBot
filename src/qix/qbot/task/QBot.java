package qix.qbot.task;

import java.util.PriorityQueue;
import java.util.Queue;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;

public abstract class QBot extends PollingScript<ClientContext> {
	
	private Queue<Task> tasks = new PriorityQueue<Task>();

	/**
	 * @return The {@link Router} for this bot
	 */
	public abstract Router getRouter();

	@Override
	public void start() {
		super.start();
		this.log.info(String.format("Starting bot %s for account %s",
				this.getName(), this.ctx.players.local().name()));
	}

	@Override
	public void poll() {
	}
	
}
