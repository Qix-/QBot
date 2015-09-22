package qix.qbot.task.stated;

public interface BotState <E extends Enum<? extends BotFlag>> {
	public boolean is(E flag);
	
	public void flag(E flag);
}
