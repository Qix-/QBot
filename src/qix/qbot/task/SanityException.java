package qix.qbot.task;

public class SanityException extends FormattedException {

	private static final long serialVersionUID = 3438366908095957603L;

	public SanityException(String format, Object... args) {
		super(format, args);
	}

	public SanityException(Throwable t, String format, Object... args) {
		super(t, format, args);
	}

}
