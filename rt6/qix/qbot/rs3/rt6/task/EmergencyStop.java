package qix.qbot.rs3.rt6.task;

import org.powerbot.script.rt6.ClientContext;

import qix.qbot.rs3.rt6.bot.BotFlag;
import qix.qbot.rs3.rt6.bot.RT6Task;
import qix.qbot.task.stated.BotState;

public class EmergencyStop extends RT6Task {


	public EmergencyStop(BotState<BotFlag> state, ClientContext ctx) {
		super(state, ctx);
	}

	@Override
	protected boolean pollTask() {
		this.log.info("performing emergency script stop");
		this.getContext().controller.stop();
		return false;
	}
	
}
