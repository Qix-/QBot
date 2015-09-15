package qix.qbot.task;

public class FormattedException extends Exception {
	private static final long serialVersionUID = -1456447197656041059L;

	public FormattedException(String format, Object... args) {
		super(String.format(format, args));
	}
	
	public FormattedException(Throwable t, String format, Object... args) {
		super(String.format(format, args), t);
	}
}
