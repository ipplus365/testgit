package com.ipplus360.exception;

public class DownLoadException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 5714976615004279698L;

	public DownLoadException(){
		super();
	}

	public DownLoadException(String message){
		super(message);
	}

	public DownLoadException(String message, Throwable cause){
		super(message, cause);
	}
	
}
