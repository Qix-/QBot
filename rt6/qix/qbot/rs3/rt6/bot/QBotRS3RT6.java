package qix.qbot.rs3.rt6.bot;

import qix.qbot.task.Task;

public abstract class QBotRS3RT6 extends QBotAdapter<BotFlag> {
	
	protected abstract Class<? extends RT6Task> getTaskClass();
	
	@Override
	protected Task getTask() {
		return RT6Task.create(this.getTaskClass(), this, this.ctx);
	}
	
	@Override
	public void start() {
		super.start();
		this.log.info(String.format("script '%s' (%s) started on account '%s'",
				this.getName(), this.getClass().getName(),
				this.ctx.players.local().name()));
	}

	@Override
	public void stop() {
		super.stop();
		this.log.info(String.format("script '%s' (%s) stopped on account '%s'",
				this.getName(), this.getClass().getName(),
				this.ctx.players.local().name()));
	}
}