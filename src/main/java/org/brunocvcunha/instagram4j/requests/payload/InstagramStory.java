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
package org.brunocvcunha.instagram4j.requests.payload;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instagram Story
 * 
 * @author Krisnamourt da Silva C. Filho
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramStory {

	private int type;
	private int story_type;
	private Map<String, Object> args;
	private Map<String, Object> inline_follow;
	private String profile_id;
	private String profile_image;
	private long timestamp;
	private String tuuid;
	private boolean clicked;
	private String profile_name;
	private InstagramRecentActivityCounts counts;
	private String pk;

}
