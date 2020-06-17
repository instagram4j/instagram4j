package com.github.instagram4j.Instagram4J.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IGChallengeInvalidCodeException extends Exception {
	
	public IGChallengeInvalidCodeException(String msg) {
		super(msg);
	}

}
