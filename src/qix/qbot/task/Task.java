package qix.qbot.task;

public interface Task {
	/**
	 * Called to update the task
	 * 
	 * @return
	 * 		True if the task is still running
	 * 		False if it's complete
	 */
	boolean poll();
	
	/**
	 * @return A description of this task
	 */
	String getDescription();
}
