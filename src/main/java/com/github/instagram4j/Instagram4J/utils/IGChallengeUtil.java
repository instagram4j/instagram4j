package com.github.instagram4j.Instagram4J.utils;

import java.util.concurrent.Callable;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.exceptions.IGChallengeException;
import com.github.instagram4j.Instagram4J.exceptions.IGChallengeInvalidCodeException;
import com.github.instagram4j.Instagram4J.exceptions.IGLoginException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.requests.IGChallengeResetRequest;
import com.github.instagram4j.Instagram4J.requests.IGChallengeSelectVerifyMethodRequest;
import com.github.instagram4j.Instagram4J.requests.IGChallengeSendCodeRequest;
import com.github.instagram4j.Instagram4J.requests.IGChallengeStateGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGChallenge;
import com.github.instagram4j.Instagram4J.responses.IGChallengeStateResponse;
import com.github.instagram4j.Instagram4J.responses.IGLoginResponse;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

@Log4j
public class IGChallengeUtil {
	
	public static IGChallengeStateResponse requestState(IGClient client, IGChallenge challenge) throws IGResponseException {
		return client.sendRequest(new IGChallengeStateGetRequest(challenge.getApi_path()));
	}
	
	public static IGChallengeStateResponse selectVerifyMethod(IGClient client, IGChallenge challenge, String method, boolean resend) throws IGResponseException {
		return client.sendRequest(new IGChallengeSelectVerifyMethodRequest(challenge.getApi_path(), method, resend));
	}
	
	public static IGLoginResponse sendSecurityCode(IGClient client, IGChallenge challenge, String code) throws IGResponseException {
		return client.sendRequest(new IGChallengeSendCodeRequest(challenge.getApi_path(), code));
	}
	
	public static IGChallengeStateResponse resetChallenge(IGClient client, IGChallenge challenge) throws IGResponseException {
		return client.sendRequest(new IGChallengeResetRequest(challenge.getApi_path()));
	}
	
	@SneakyThrows
	public static IGLoginResponse resolve(IGClient client, IGChallenge challenge, Callable<String> inputCode) throws IGLoginException, IGChallengeException, IGChallengeInvalidCodeException {
		IGChallengeStateResponse stateResponse = requestState(client, challenge);
		String name = stateResponse.getStep_name();
		
		if (name.equalsIgnoreCase("select_verify_method")) {
			selectVerifyMethod(client, challenge, stateResponse.getStep_data().getChoice(), false);
			log.info("select_verify_method option security code sent to " + (stateResponse.getStep_data().getChoice().equals("1") ? "email" : "phone"));
		} else if (name.equalsIgnoreCase("delta_login_review")) {
			selectVerifyMethod(client, challenge, "0", false);
			log.info("delta_login_review option sent choice 0");
			return client.sendLoginRequest();
		} else {
			throw new IGChallengeException("Unknown step_name");
		}
		
		try {
			IGLoginResponse loginResponse = sendSecurityCode(client, challenge, inputCode.call());
			if (loginResponse.getStatus().equalsIgnoreCase("ok")) {
				log.info("challenge success");
			} else {
				log.info("Wrong security code");
				throw new IGChallengeInvalidCodeException(loginResponse.getMessage());
			}
			
			return loginResponse;
		} catch (Exception exception) {
			throw new IGChallengeException(exception);
		}
	}
}
