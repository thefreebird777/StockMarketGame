package app.main.exceptions;

public class APIException extends Exception {
	private int code;
	private String message;
	
	public APIException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public APIException() { }
	
	public int getCode() { return this.code; }
	public String getMessage() { return this.message; }
	public void setCode(int code) { this.code = code; }
	public void setMessage(String message) { this.message = message; }
}