package com.github.instagram4j.Instagram4J;

import java.net.CookieManager;
import java.net.CookiePolicy;

import com.github.instagram4j.Instagram4J.exceptions.IGLoginException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException;
import com.github.instagram4j.Instagram4J.exceptions.IGResponseException.IGExceptionInfo;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.IGUser;
import com.github.instagram4j.Instagram4J.requests.IGLoginRequest;
import com.github.instagram4j.Instagram4J.requests.IGRequest;
import com.github.instagram4j.Instagram4J.responses.IGLoginResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@Log4j
public class IGClient {
	private final String username;
	private final String password;
	@Getter
	private OkHttpClient httpClient;
	@Getter
	private CookieManager cookieManager;
	@Getter
	private String deviceId;
	@Getter
	private String guid;
	@Getter
	private boolean loggedIn;
	@Getter
	private IGUser selfUser;

	// logging
	private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((msg) -> {
		log.debug(msg);
	}).setLevel(Level.BODY);
	
	public IGClient(String username, String password) {
		this.username = username;
		this.password = password;
		this.guid = IGUtils.randomUuid();
		this.deviceId = IGUtils.generateDeviceId(username, password);

		this.cookieManager = new CookieManager();
		this.cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		this.httpClient = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(this.cookieManager))
				.addInterceptor(loggingInterceptor).build();

	}
	
	@SneakyThrows
	public IGLoginResponse login() throws IGLoginException {
		log.debug("Logging in. . .");
		IGLoginRequest login = new IGLoginRequest(username, password);
		IGLoginResponse res = this.sendRequest(login);
		log.debug("Response is : " + res.getStatus());
		if (res.getStatus().equals("ok")) setLoggedInState(res);
		else throw new IGLoginException(res);
		
		return res;
	}

	@SneakyThrows
	public <T> T sendRequest(IGRequest<T> req) throws IGResponseException {
		req.setClient(this);
		Response res = httpClient.newCall(req.formRequest()).execute();

		try (ResponseBody body = res.body()) {
			return req.parseResponse(body.string());
		} catch (Exception ex) {
			throw new IGResponseException(IGExceptionInfo.builder().response(res).build(), "Unable to parse to IGResponse", ex);
		}
	}
	
	public void setLoggedInState(IGLoginResponse state) {
		this.loggedIn = true;
		this.selfUser = state.getLogged_in_user();
	}

	public String getCsrfToken() {
		return IGUtils.getCookieValue(this.cookieManager.getCookieStore(), "csrftoken").orElse("missing");
	}

	public IGPayload setIGLoad(IGPayload load) {
		load.set_csrftoken(this.getCsrfToken());
		load.setDevice_id(this.deviceId);
		load.setGuid(this.guid);
		
		return load;
	}
}
