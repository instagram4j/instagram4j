/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchLocationsResult;

/**
 * Search Locations Request
 *
 * @author Yumaev
 */
@RequiredArgsConstructor
public class InstagramSearchLocationsRequest extends InstagramGetRequest<InstagramSearchLocationsResult> {

	@NonNull private final String latitude;
	@NonNull private final String longitude;
	@NonNull private final String query;
	private final String timestamp = String.valueOf(System.currentTimeMillis());

	@Override
	public String getUrl() {
		return "location_search/?ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION
				+ "&search_query=" + query
				+ "&rank_token=" + api.getRankToken()
				+ "&latitude=" + latitude
				+ "&longitude=" + longitude
				+ "&timestamp=" + timestamp;
	}

	@Override
	@SneakyThrows
	public InstagramSearchLocationsResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramSearchLocationsResult.class);
	}

}
