package com.example.blog.exception;


import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException { 

	private static final long serialVersionUID = 1L;

	public BaseException() {
		super();
	}

	private String code = "500";

	private String message = "";

	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	private Object data = null;


}