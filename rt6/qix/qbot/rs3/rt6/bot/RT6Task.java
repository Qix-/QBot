package qix.qbot.rs3.rt6.bot;

import java.util.logging.Logger;

import org.powerbot.script.rt6.ClientContext;

import qix.qbot.task.stated.BotState;
import qix.qbot.task.stated.StatedTask;

public abstract class RT6Task extends StatedTask<BotFlag> implements ContextAccess {
	
	private final ClientContext ctx;
	private final BotState<BotFlag> state;
	protected final Logger log;
	
	public RT6Task(ContextAccess accessor, String description) {
		super(accessor.getState(), description);
		this.ctx = accessor.getContext();
		this.state = accessor.getState();
		this.log = Logger.getLogger(String.format("[%s <%s>]",
				this.ctx.players.local().name(), this.getClass().getSimpleName()));
	}
	
	@Override
	public BotState<BotFlag> getState() {
		return this.state;
	}

	@Override
	public ClientContext getContext() {
		return this.ctx;
	}
}
