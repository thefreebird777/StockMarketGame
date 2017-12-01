package app.exceptions;

/**
 * @author rdevitto
 */
public class APIException extends Exception {

    private int code;
    private String message;
    private String location;
    
    public APIException(int code, String message, String location) {
        this.code = code;
        this.message = message;
        this.location = location;
    }
    
    public APIException() {}

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
