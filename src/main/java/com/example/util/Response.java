package com.example.util;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class Response<T> {
	private String message;
	private String status;
	private T body;
	
	public Response(String message, String status, T body) {
		this.message = message;
		this.status = status;
		this.body = body;
	}
	
	public Response(String message, String status) {
		this.message = message;
		this.status = status;
	}
}
