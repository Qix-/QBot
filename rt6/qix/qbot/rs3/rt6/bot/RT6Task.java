package qix.qbot.rs3.rt6.bot;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.powerbot.script.rt6.ClientContext;

import qix.qbot.task.Task;
import qix.qbot.task.stated.BotState;
import qix.qbot.task.stated.StatedTask;

public abstract class RT6Task extends StatedTask<BotFlag> {
	
	private final ClientContext ctx;
	protected final Logger log;

	public RT6Task(BotState<BotFlag> state, ClientContext ctx) {
		super(state);
		this.ctx = ctx;
		this.log = Logger.getLogger(String.format("[%s <%s>]",
				this.ctx.players.local().name(), this.getClass().getSimpleName()));
	}
	
	protected ClientContext getContext() {
		return this.ctx;
	}

	public static Task create(Class<? extends RT6Task> task, BotState<BotFlag> state, ClientContext ctx) {
		Constructor<? extends RT6Task> con;
		try {
			con = task.getConstructor(BotState.class, ClientContext.class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("task is missing <init>(BotState, rt6.ClientContext): "
					+ task.getName(), e);
		}
		
		try {
			return con.newInstance(state, ctx);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("couldn't create RS3 task instance: " + task.getName(), e);
		}
	}
}
