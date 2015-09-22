package qix.qbot.rs3.rt6.task;

import qix.qbot.rs3.rt6.bot.ContextAccess;
import qix.qbot.rs3.rt6.bot.RT6Task;

public class EmergencyStop extends RT6Task {
	
	private String reason;

	public EmergencyStop(ContextAccess accessor) {
		super(accessor, "stops the bot completely");
		this.reason = "";
	}
	
	public EmergencyStop(ContextAccess accessor, String fmt, Object... args) {
		this(accessor);
		this.reason = String.format(fmt, args);
	}
	
	@Override
	protected boolean pollTask() {
		this.log.info("performing emergency script stop" +
				(this.reason == null ? "" : ": " + this.reason));
		this.getContext().controller.stop();
		return false;
	}
	
}
