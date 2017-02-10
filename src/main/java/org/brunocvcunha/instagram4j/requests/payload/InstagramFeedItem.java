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
import java.util.Map;

import lombok.Data;

/**
 * Tag Feed Results
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Data
public class InstagramFeedItem {

    public long taken_at;
    public long pk;
    public String id;
    public long device_timestamp;
    public int media_type;
    public String code;
    public String client_cache_key;
    public int filter_type;
    public boolean has_audio;
    public double video_duration;
    public Map<String, Object> attribution;
    public List<Object> video_versions;
    public Map<String, Object> image_versions2;
    public Map<String, Object> usertags;
    public Map<String, Object> location;
    public float lng;
    public float lat;
    public int original_width;
    public int original_height;
    public int view_count;
    public Map<String, Object> user;
//        "username": "naayarafreitassmakeup",
//        "has_anonymous_profile_picture": false,
//        "is_unpublished": false,
//        "friendship_status": {
//            "outgoing_request": false,
//            "following": false
//        },
//        "profile_pic_id": "1441364815238182249_227966303",
//        "profile_pic_url": "http://instagram.fsnc1-1.fna.fbcdn.net/t51.2885-19/s150x150/16465820_383579565337101_5960924374985867264_a.jpg",
//        "pk": 227966303,
//        "is_private": false,
//        "is_favorite": false,
//        "full_name": "Nayara Freitas Make Up"

    public String organic_tracking_token;
    public int like_count;
    public List<String> top_likers;
    public List<InstagramUserSummary> likers;
    public boolean has_liked;
    public boolean comment_likes_enabled;
    public boolean has_more_comments;
    public long next_max_id;
    public int max_num_visible_preview_comments;
    public List<Object> preview_comments;
    public List<Object> comments;
    public boolean comment_count;
    public Map<String, Object> caption;

//    "caption": {
//        "content_type": "comment",
//        "user": {
//            "username": "naayarafreitassmakeup",
//            "has_anonymous_profile_picture": false,
//            "is_unpublished": false,
//            "friendship_status": {
//                "outgoing_request": false,
//                "following": false
//            },
//            "profile_pic_id": "1441364815238182249_227966303",
//            "profile_pic_url": "http://instagram.fsnc1-1.fna.fbcdn.net/t51.2885-19/s150x150/16465820_383579565337101_5960924374985867264_a.jpg",
//            "pk": 227966303,
//            "is_private": false,
//            "is_favorite": false,
//            "full_name": "Nayara Freitas Make Up"
//        },
//        "bit_flags": 0,
//        "user_id": 227966303,
//        "type": 1,
//        "has_translation": true,
//        "created_at_utc": 1486242818,
//        "created_at": 1486242818,
//        "status": "Active",
//        "media_id": 1443031802759855000,
//        "text": "Make na Formanda linda de hoje 🎀💄 #Makeup #maquiagemlovers #Maquiagem #Mac #maccosmetics #Formanda #makenoite #boanoite #formatura #marykay #catharinehill #Anastasiabeverlyhills #loucasporbatom #instamake #makeupNayarafreitas",
//        "pk": 17871452926045222
//    },
    
    
    public boolean caption_is_edited;
    public boolean photo_of_you;
    public boolean comments_disabled;
    
}
