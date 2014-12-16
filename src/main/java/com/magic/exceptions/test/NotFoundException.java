package com.magic.exceptions.test;


//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found ...!")
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7444581334764294410L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
