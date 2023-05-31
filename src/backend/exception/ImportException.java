package backend.exception;

public class ImportException extends BasicException{

	private static final long serialVersionUID = 909325508583342894L;

	
	public ImportException() {
    }

    public ImportException(String message) {
        super("Word Import Exception:\t" + message);
    }

}
