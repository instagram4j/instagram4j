package com.github.instagram4j.Instagram4J;

import java.io.IOException;
import java.util.Optional;

import com.github.instagram4j.Instagram4J.requests.IGLoginRequest;
import com.github.instagram4j.Instagram4J.requests.IGRequest;
import com.github.instagram4j.Instagram4J.responses.IGLoginResponse;
import com.github.instagram4j.Instagram4J.responses.IGResponseException;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@Log4j
public class Instagram4J {
	private final String username;
	private final String password;
	@Getter
	private OkHttpClient httpClient;
	@Getter
	private String deviceId;
	@Getter
	private String uuid;
	@Getter
	private boolean loggedIn;
	
	//logging
	private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((msg) -> {
		log.debug(msg);
	});

	public Instagram4J(String username, String password) {
		this.username = username;
		this.password = password;
		this.uuid = IGUtils.randomUuid();
		this.deviceId = IGUtils.generateDeviceId(username, password);
		
		loggingInterceptor.setLevel(Level.BODY);
		this.httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
	}

	public IGLoginResponse login() throws IOException, IGResponseException {
		log.debug("Logging in. . .");
		IGLoginRequest login = new IGLoginRequest(username, password);
		IGLoginResponse res = this.sendRequest(login);
		log.debug("Response is : " + res.getStatus());

		return res;
	}

	public <T> T sendRequest(IGRequest<T> req) throws IGResponseException, IOException {
		req.setClient(this);
		Response res = httpClient.newCall(req.formRequest()).execute();
		
		try (ResponseBody body = res.body()) {
			return req.parseResponse(body.string());
		} catch (Exception ex) {
			throw new IGResponseException(res.code(), "Unable to parse to IGResponse", ex);
		}
	}
}
