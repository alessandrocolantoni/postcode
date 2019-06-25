package it.wcc.exception;

 


public class ServiceException extends RuntimeException {

 
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unused")
	private Exception exception;

    public ServiceException(String message, Exception ex) {
        super(message);
        this.exception = ex;
    }

    public ServiceException(String message, Throwable t) {
        super(message, t);
    }
}