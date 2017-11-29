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

import org.brunocvcunha.instagram4j.requests.payload.InstagramGetReelsTrayFeedResult;

import lombok.SneakyThrows;

/**
 * Get Story Request
 *
 * @author Ozan Karaali
 *
 */
public class InstagramGetReelsTrayFeedRequest extends InstagramGetRequest<InstagramGetReelsTrayFeedResult>{
    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public String getUrl() {
        return "feed/reels_tray/?";
    }

    @Override
    @SneakyThrows
    public InstagramGetReelsTrayFeedResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramGetReelsTrayFeedResult.class);
    }
}
