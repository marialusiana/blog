package com.example.blog.exception;
import org.springframework.http.HttpStatus;

/* 
* The 500 (Internal Server Error) status code indicates that the server
* encountered an unexpected condition that prevented it from fulfilling
* the request.
*/
public class InternalServerErrorException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException() {
		this.setCode("500");
		this.setMessage("Internal Server Error");
		this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public InternalServerErrorException(String message) {
		this.setCode("500");
		this.setMessage(message);
		this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public InternalServerErrorException(String message, String code) {
		this.setCode(code);
		this.setMessage(message);
		this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}