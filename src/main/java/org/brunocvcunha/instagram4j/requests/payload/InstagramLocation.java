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
