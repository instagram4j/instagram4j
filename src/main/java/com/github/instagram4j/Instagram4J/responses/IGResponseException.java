package com.github.instagram4j.Instagram4J.responses;

import lombok.Getter;

public class IGResponseException extends Exception {
	@Getter
	private final int status;
	@Getter
	private final Exception exception;
	public IGResponseException(int status, String message, Exception pass) {
		super(message);
		this.status = status;
		this.exception = pass;
	}
}
