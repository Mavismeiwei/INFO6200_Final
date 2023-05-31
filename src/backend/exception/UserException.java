package backend.exception;

public class UserException extends BasicException{

	private static final long serialVersionUID = 1L;

	public UserException(){}
	
	public UserException(String message) {
        super(message);
    }
	
}
