package qix.qbot.rs3.rt6.bot;

import qix.qbot.task.stated.BotFlag;

public enum RT6BotFlag implements BotFlag {
	IN_BANK("player is within the banking interface"),
	BANK_AUTHED("player has been authorized to use the bank (pin entered)")
	;
	
	final String description;
	
	RT6BotFlag(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
