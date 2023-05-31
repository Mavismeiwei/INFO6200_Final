package backend.exception;

public class CustomSQLException extends BasicException{
	
	private static final long serialVersionUID = 6874731508048280328L;

	public CustomSQLException() {}

    public CustomSQLException(String message) {
        super(message);
    }
    
    
}
