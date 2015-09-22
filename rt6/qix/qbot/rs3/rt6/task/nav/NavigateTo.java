package qix.qbot.rs3.rt6.task.nav;

import org.powerbot.script.Tile;

import qix.qbot.rs3.rt6.bot.ContextAccess;
import qix.qbot.rs3.rt6.bot.RT6Task;
import qix.qbot.rs3.rt6.task.EmergencyStop;
import qix.qbot.task.Priority;

public class NavigateTo extends RT6Task {
	
	private final Tile tile;
	
	public NavigateTo(ContextAccess accessor, Tile tile) {
		super(accessor, String.format("navigates to (%d, %d)", tile.x(), tile.y()));
		this.tile = this.getContext().movement.closestOnMap(tile);
		
		Tile playerTile = this.getContext().players.local().tile();
		if (!this.getContext().movement.reachable(playerTile, this.tile)) {
			throw new RuntimeException(
					String.format("invalid movement: cannot move from current position {%d, %d} to {%d, %d} (distance: %d)",
							this.tile.x(), this.tile.y(),
							playerTile.x(), playerTile.y(),
							this.getContext().movement.distance(playerTile, this.tile)));
		}
	}

	public NavigateTo(ContextAccess accessor, int x, int y) {
		this(accessor, new Tile(x, y));
	}
	
	@Override
	protected boolean pollTask() {
		boolean stepped = this.getContext().movement.step(this.tile);
		Tile playerTile = this.getContext().players.local().tile();
		if (!stepped && !this.tile.equals(playerTile)) {
			this.addTask(new EmergencyStop(this, "navigation failed to {%d, %d} (ended at {%d, %d})",
					this.tile.x(), this.tile.y(),
					playerTile.x(), playerTile.y()),
					Priority.CRITICAL);
			return false;
		}
		return false;
	}

}
