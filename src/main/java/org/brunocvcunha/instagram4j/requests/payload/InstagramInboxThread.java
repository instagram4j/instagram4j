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

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Inbox Thread
 * 
 * @author Krisnamourt da Silva C. Filho
 *
 */
@Getter	
@Setter
@ToString(callSuper = true)
public class InstagramInboxThread {

	public String thread_id; 
	public String thread_v2_id;    
	public InstagramUser inviter;
	public List<InstagramUser> users;
	public List<InstagramUser> left_users;
	public List<InstagramInboxThreadItem> items;
	public long last_activity_at;
	public boolean muted;
	public boolean is_pin;
	public boolean named;
	public boolean canonical;
	public boolean pending;
	public boolean valued_request;
    public String thread_type;
    public long viewer_id;
    public String thread_title;
    public long pending_score;
    public int reshare_send_count;
    public int reshare_receive_count;
    public int expiring_media_send_count;
    public int expiring_media_receive_count;
    public boolean has_older;
    public boolean has_newer;
    public String newest_cursor;
    public String oldest_cursor;
    public boolean is_spam;
}
