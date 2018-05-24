package com.ipplus360.exception;

public class OrderException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5714976615004279698L;

	public OrderException(){
		super();
	}
	
	public OrderException(String message){
		super(message);
	}
	
	public OrderException(String message, Throwable cause){
		super(message, cause);
	}
	
}
