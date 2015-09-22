package qix.qbot.rs3.rt6.bot;

import java.util.HashSet;
import java.util.Set;

import qix.qbot.task.stated.BotFlag;
import qix.qbot.task.stated.BotState;

public abstract class QBotAdapter <E extends Enum<? extends BotFlag>> extends QBot implements BotState<E> {
	
	private final Set<E> flags = new HashSet<>();

	@Override
	public boolean is(E flag) {
		return this.flags.contains(flag);
	}

	@Override
	public void flag(E flag) {
		this.flags.add(flag);
	}
}
