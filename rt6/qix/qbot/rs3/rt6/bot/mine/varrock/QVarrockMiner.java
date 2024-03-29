package qix.qbot.rs3.rt6.bot.mine.varrock;

import java.util.logging.Level;

import org.powerbot.script.Script;

import qix.qbot.rs3.rt6.bot.ContextAccess;
import qix.qbot.rs3.rt6.bot.QBotRS3RT6;
import qix.qbot.rs3.rt6.bot.RT6Task;
import qix.qbot.rs3.rt6.task.EmergencyStop;
import qix.qbot.rs3.rt6.task.nav.Locations;
import qix.qbot.rs3.rt6.task.nav.NavigateTo;
import qix.qbot.task.Priority;
import qix.qbot.task.Task;

@Script.Manifest(name="QBot::VarrockMiner <main>", description="QBot Varrock Miner - host script. Use with a droid.")
public class QVarrockMiner extends QBotRS3RT6 {
	public QVarrockMiner() {
		this.log.setLevel(Level.ALL);
	}
	
	@Override
	protected Task getInitTask() {
		return new MainTask(this);
	}
	
	private static class MainTask extends RT6Task {

		public MainTask(ContextAccess accessor) {
			super(accessor, "main");
			this.addTask(new NavigateTo(this, Locations.VARROCK_BANK_EAST));
			this.addTask(new EmergencyStop(this, "script finished"), Priority.IDLE);
		}

		@Override
		protected boolean pollTask() {
			return false;
		}
		
	}
}
