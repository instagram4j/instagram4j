package com.github.instagram4j.Instagram4J.exceptions;

import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;

import lombok.Getter;

@Getter
public class IGLoginException extends Exception {
	private IGLoginResponse response;
	
	public IGLoginException(IGLoginResponse body) {
		super(body.getMessage());
		this.response = body;
	}

}