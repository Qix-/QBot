package qix.qbot.task;

import java.util.Comparator;

public enum Priority {
	/**
	 * Must happen <strong>immediately</strong>.
	 * 
	 * Interrupts all.
	 */
	CRITICAL(1000, Integer.MAX_VALUE),
	
	/**
	 * Must happen at next available request.
	 * Does not interrupt <code>HIGH</code> priorities.
	 */
	URGENT(500, 100),
	
	/**
	 * High priority; not interrupted by <code>URGENT</code>.
	 */
	HIGH(100),
	
	/**
	 * Requests; performed at earliest convenience.
	 */
	REQUEST(75),

	/**
	 * Normal priority. Most tasks should go here.
	 */
	NORMAL(50),
	
	/**
	 * Low priority. Good for sanity checks.
	 */
	LOW(10),
	
	/**
	 * Lowest priority. Run after absolutely everything else
	 * is completed.
	 */
	LOWEST(0),
	
	/**
	 * Run when the character is idle.
	 */
	IDLE(-1)
	;
	
	private final int level;
	private final int interruptedBy;
	
	Priority(final int level) {
		this(level, level);
	}

	Priority(final int level, final int interruptedBy) {
		this.level = level;
		this.interruptedBy = interruptedBy;
	}
	
	/**
	 * @return The integer level of this priority
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * Checks whether or not the given <code>priority</code> can interrupt
	 * this priority. Used for checking if a new {@link Task} can interrupt the
	 * currently running Task.
	 * @param priority The priority to check
	 * @return <code>true</code> if the given priority should interrupt
	 * 		this one; <code>false</code> otherwise
	 */
	public boolean isInterruptedBy(final Priority priority) {
		return priority.level > this.interruptedBy;
	}
	
	/**
	 * @return A {@link Comparator} that can be used to sort these priorities.
	 */
	public static Comparator<Priority> getComparator() {
		return new Comparator<Priority>() {
			@Override
			public int compare(Priority o1, Priority o2) {
				return o2.level - o1.level;
			}
		};
	}
}
