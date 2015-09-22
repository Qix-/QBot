package qix.qbot.rs3.rt6.bot;

import org.powerbot.script.rt6.ClientContext;

import qix.qbot.task.Task;
import qix.qbot.task.stated.BotState;


public abstract class QBotRS3RT6 extends QBotAdapter<BotFlag> implements ContextAccess {
	
	@Override
	public void start() {
		super.start();
		this.log.info(String.format("script '%s' (%s) started on account '%s'",
				this.getName(), this.getClass().getName(),
				this.ctx.players.local().name()));
	}
	
	protected abstract Task getInitTask();

	@Override
	public BotState<BotFlag> getState() {
		return this;
	}
	
	@Override
	public ClientContext getContext() {
		return this.ctx;
	}
	
	@Override
	protected final Task getTask() {
		Task task = this.getInitTask();

		return task;
	}

	@Override
	public void stop() {
		super.stop();
		this.log.info(String.format("script '%s' (%s) stopped on account '%s'",
				this.getName(), this.getClass().getName(),
				this.ctx.players.local().name()));
	}
}