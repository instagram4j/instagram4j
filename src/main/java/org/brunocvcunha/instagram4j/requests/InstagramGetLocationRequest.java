/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import org.brunocvcunha.instagram4j.requests.payload.InstagramVenueResult;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Location Request
 * 
 * @author Krisnamourt da Silva C Filho
 *
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramGetLocationRequest extends InstagramGetRequest<InstagramVenueResult> {

	private String lat;
	private String lng;

	@Override
	public String getUrl() {

		String baseUrl = "location_search/?rank_token="+api.getRankToken()+"&latitude="+lat+"&longitude="+lng  ;

		return baseUrl;
	}

	@Override
	@SneakyThrows
	public String getPayload() {
		return null;
	}

	@Override
	@SneakyThrows
	public InstagramVenueResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramVenueResult.class);
	}

}
