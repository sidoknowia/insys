package com.insys.exceptions;

public class UnableToAddNodeException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnableToAddNodeException(){}
	
	public UnableToAddNodeException(String msg) {
		super(msg);
	}
}
