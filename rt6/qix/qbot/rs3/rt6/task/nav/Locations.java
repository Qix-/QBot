package qix.qbot.rs3.rt6.task.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.powerbot.script.Tile;

/**
 * Common locations
 * @author qix
 *
 */
public enum Locations {
	VARROCK_BANK_EAST(
			new Area(3251, 3420, 3254, 3422), // main area
			new Area(3255, 3420, 3257, 3420), // area by the staircase
			new Area(3253, 3423, 3254, 3424)  // doorway
			)
	;
	
	private static final Random rng = new Random();
	private final List<Tile> tiles = new ArrayList<>();
	
	Locations(Area... areas) {
		for (Area area : areas) {
			area.makeTiles(this.tiles);
		}
	}
	
	public Tile getRandom() {
		return this.tiles.get(rng.nextInt(this.tiles.size()));
	}
	
	static class Area {
		public final int x1;
		public final int y1;
		public final int x2;
		public final int y2;
		
		public Area(int x, int y) {
			this.x1 = this.x2 = x;
			this.y1 = this.y2 = y;
		}
		
		public Area(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
		public void makeTiles(List<Tile> list) {
			for (int x = this.x1; x <= this.x2; x++) {
				for (int y = this.y1; y <= this.y2; y++) {
					list.add(new Tile(x, y));
				}
			}
		}
	}
}
