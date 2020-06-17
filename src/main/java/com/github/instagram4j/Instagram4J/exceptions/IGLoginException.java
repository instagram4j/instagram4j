package com.github.instagram4j.Instagram4J.exceptions;

import com.github.instagram4j.Instagram4J.responses.IGLoginResponse;

import lombok.Getter;

@Getter
public class IGLoginException extends Exception {
	private IGLoginResponse response;
	
	public IGLoginException(IGLoginResponse body) {
		this.response = body;
	}

}
