package qix.qbot.rs3.rt6.bot.mine.varrock;

import org.powerbot.script.Script;

import qix.qbot.rs3.rt6.bot.QBotRS3RT6;
import qix.qbot.rs3.rt6.bot.RT6Task;
import qix.qbot.rs3.rt6.task.EmergencyStop;

@Script.Manifest(name="QBot::VarrockMiner <main>", description="QBot Varrock Miner - host script. Use with a droid.")
public class QVarrockMiner extends QBotRS3RT6 {
	public QVarrockMiner() {
		System.out.println("Bot init");
	}

	@Override
	protected Class<? extends RT6Task> getTaskClass() {
		return EmergencyStop.class;
	}
}
