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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instagram Location
 * 
 * @author Krisnamourt da Silva C. Filho
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramLocation {

	private String lat;
	private String lng;
	private String address;
	private String external_id;
	private String external_id_source;
	private String name;
	private int minimum_age;

	private String external_source;
	private String facebook_places_id;
	private String city;
	private String pk;
	private String short_name;
	private String facebook_events_id;
	private String start_time;
	private String end_time;
	private String type;
	private String profile_pic_url;
	private String profile_pic_username;
	private String time_granularity;
	private String timezone;
	private int country;
	private String created_at;
	private int event_category;
	private String place_fbid;
	private String place_name;

}
