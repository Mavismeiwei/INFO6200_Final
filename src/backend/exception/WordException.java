package backend.exception;

public class WordException extends BasicException{
	private static final long serialVersionUID = 7187814315840975279L;

	public WordException() {
    }

    public WordException(String message) {
        super("Word Exception:\t" + message);
    }
}
