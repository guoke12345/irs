package com.framework.core.exception;
/**
 * checked Exception的子类
 * 所有系统中checked类都要集成该类
 * @author gaofeng
 *
 */
public class BaseAppException extends Exception {

	private static final long serialVersionUID = 8343048459443313229L;
	/**
	 * 异常错误码
	 */
	private String errorCode;
	
    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    ///////////////////////////////////////////////
	//   构造方法
	///////////////////////////////////////////////
	public BaseAppException() {
		super();
	}
	
	public BaseAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseAppException(String errorCode, String message, Throwable cause) {
		this(message, cause);
		setErrorCode(errorCode);
	}
	
	public BaseAppException(String message) {
		super(message);
	}

	public BaseAppException(Throwable cause) {
	    super(cause);
	}
	
	@Override
	public String toString() {
		String errorCode = getErrorCode();
		String s = (errorCode != null) ? errorCode + "--" + getClass().getName() : getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : s;
	}
	
}
