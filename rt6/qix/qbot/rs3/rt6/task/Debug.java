package qix.qbot.rs3.rt6.task;

import java.util.logging.Level;

import org.powerbot.script.Tile;

import qix.qbot.rs3.rt6.bot.ContextAccess;
import qix.qbot.rs3.rt6.bot.RT6Task;

public final class Debug {
	private Debug() {}
	
	public static class PlayerPosition extends RT6Task {

		public PlayerPosition(ContextAccess accessor) {
			super(accessor, "logs the player position");
			this.log.setLevel(Level.ALL);
		}

		@Override
		protected boolean pollTask() {
			Tile playerTile = this.getContext().players.local().tile();
			this.log.fine(String.format("{%d, %d, %d}", playerTile.x(), playerTile.y(), playerTile.floor()));
			return true;
		}
	}
}
