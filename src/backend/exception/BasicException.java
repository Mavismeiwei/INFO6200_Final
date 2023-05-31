package backend.exception;

public class BasicException extends RuntimeException{
	
	private static final long serialVersionUID = -7879302866926915906L;
	
	private String msg;
	
	public BasicException() {
    }

    public BasicException(String message) {
        super(message);
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
