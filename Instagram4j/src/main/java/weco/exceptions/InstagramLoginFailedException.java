package weco.exceptions;
/**
 * 
 * @author Weco
 *
 */

public class InstagramLoginFailedException extends Exception{
	public InstagramLoginFailedException() { super(); }
	public InstagramLoginFailedException(String message) { super(message); }
	public InstagramLoginFailedException(String message, Throwable cause) { super(message, cause); }
	public InstagramLoginFailedException(Throwable cause) { super(cause); }
}
