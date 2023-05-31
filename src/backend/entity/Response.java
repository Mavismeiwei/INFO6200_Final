package backend.entity;


public class Response {
	private boolean flag;// success or fail
	private String message; // exception message or other message
	private int type;//1. SQLException 2. UserException 3. WordException 4. Unknown Exception(put it into BasicException)? 0. SUCCESS
	
	
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
