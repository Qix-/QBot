package qix.qbot.rs3.rt6.bot;

import org.powerbot.script.rt6.ClientContext;

import qix.qbot.task.stated.BotState;

public interface ContextAccess {
	public ClientContext getContext();
	
	public BotState<BotFlag> getState();
}
