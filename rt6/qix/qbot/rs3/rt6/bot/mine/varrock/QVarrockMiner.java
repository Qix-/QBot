package qix.qbot.rs3.rt6.bot.mine.varrock;

import org.powerbot.script.Script;

import qix.qbot.rs3.rt6.bot.QBotRS3RT6;
import qix.qbot.task.Task;
import qix.qbot.task.TaskQueue;

@Script.Manifest(name="Test Impl", description="Test QBot impl")
public class QVarrockMiner extends QBotRS3RT6 {
	
	public QVarrockMiner() {
		System.out.println("Bot init");
	}

	@Override
	protected Task getTask() {
		return new TaskQueue("dummy");
	}

}
