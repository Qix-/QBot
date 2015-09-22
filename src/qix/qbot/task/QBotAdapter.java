package qix.qbot.task;

import java.util.HashSet;
import java.util.Set;

import qix.qbot.task.stated.BotFlag;
import qix.qbot.task.stated.BotState;

public class QBotAdapter <E extends Enum<? extends BotFlag>> extends QBot implements Router, BotState<E> {
	
	private final Set<E> flags = new HashSet<>();

	@Override
	public Task sanityCheck() {
		return null;
	}

	@Override
	public Task hardReset() {
		return null;
	}

	@Override
	public Router getRouter() {
		return this;
	}

	@Override
	public boolean is(E flag) {
		return this.flags.contains(flag);
	}

	@Override
	public void flag(E flag) {
		this.flags.add(flag);
	}
}
