package qix.qbot.rs3.rt6.task.nav;

import java.util.logging.Level;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.LocalPath;

import qix.qbot.rs3.rt6.bot.ContextAccess;
import qix.qbot.rs3.rt6.bot.RT6Task;

public class NavigateTo extends RT6Task {
	
	private final Tile tile;
	private final LocalPath path;
	
	public NavigateTo(ContextAccess accessor, Tile tile) {
		super(accessor, String.format("navigates to (%d, %d)", tile.x(), tile.y()));
		this.log.setLevel(Level.ALL);
		
		this.tile = tile;
		this.path = this.getContext().movement.findPath(this.tile);
		
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
	
	public NavigateTo(ContextAccess accessor, Locations location) {
		this(accessor, location.getRandom());
	}
	
	@Override
	protected boolean pollTask() {
		if (this.getContext().players.local().inMotion()) {
			return true;
		}
		
		Tile next = this.path.next();
		this.log.fine(String.format("stepping towards {%d, %d} (destination {%d, %d})",
				next.x(), next.y(),
				this.tile.x(), this.tile.y()));
		
		boolean stepped = this.getContext().movement.step(next);

		if (!stepped) {
			this.log.warning(String.format("could not step towards {%d, %d} (destination {%d, %d})",
					next.x(), next.y(),
					this.tile.x(), this.tile.y()));
			return false;
		}

		if (next.equals(this.path.end()) || next.equals(this.tile)) {
			return false;
		}

		return true;
	}

}
