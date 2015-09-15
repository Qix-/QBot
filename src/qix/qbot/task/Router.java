package qix.qbot.task;

public interface Router {
	/**
	 * Performed periodically to perform an overall "sanity" assessment.
	 * 
	 * This assessment performs assertions to make sure the bot isn't
	 * spuriously out of control.
	 * 
	 * @return The sanity check task, or <code>null</code> for no sanity check
	 */
	public Task sanityCheck();
	
	/**
	 * Performed whenever a hard reset needs to occur; usually performed whenever
	 * a <code>sanityCheck()</code> fails.
	 */
	public Task hardReset();
}
