package qix.qbot.rs3.rt6.bot;

public enum BotFlag implements qix.qbot.task.stated.BotFlag {
	IN_BANK("player is within the banking interface"),
	BANK_AUTHED("player has been authorized to use the bank (pin entered)")
	;
	
	final String description;
	
	BotFlag(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}
