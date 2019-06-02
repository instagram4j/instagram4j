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
package org.brunocvcunha.instagram4j.storymetadata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.requests.payload.Hashtag;

/**
 * StoryHashtag
 * @author Justin Vo
 *
 * Note: Only makes area interactive. Does not apply 'sticker'; it has to be drawn manually!
 */
@Builder
public class StoryHashtag extends StoryMetadata {

    /**
     * Hashtag data
     */
    private Hashtag hashtag;
    /**
     * Hashtag name without '#'
     */
    private String tag_name;
    /**
     * x location 0.0 - 1.0
     */
    @Builder.Default private double x = 0.5;
    /**
     * y location 0.0 - 1.0
     */
    @Builder.Default private double y = 0.5;
    /**
     * z location 0.0 - 1.0
     */
    @Builder.Default private double z = 0;
    /**
     * rotation 0.0 - 1.0
     */
    @Builder.Default private double rotation = 0;
    /**
     * height 0.0 - 1.0
     */
    @Builder.Default private double height = 1.0;
    /**
     * width 0.0 - 1.0
     */
    @Builder.Default private double width = 1.0;
    /**
     * Values that are modifiable
     */
    private final boolean is_pinned = false;
    private final boolean is_sticker = true;
    private final String custom_title = "";
    private final boolean use_custom_title = false;
    
    private List<Object> map(){
        Map<String, Object> hashtag = new HashMap<>();
        
        hashtag.put("tag_name", tag_name.toUpperCase());
        hashtag.put("x", x);
        hashtag.put("y", y);
        hashtag.put("z", z);
        hashtag.put("rotation", rotation);
        hashtag.put("height", height);
        hashtag.put("width", width);
        hashtag.put("is_pinned", is_pinned);
        hashtag.put("is_sticker", is_sticker);
        hashtag.put("custom_title", custom_title);
        hashtag.put("use_custom_title", use_custom_title);
        
        return Arrays.asList(hashtag);
    }
    
    @Override
    public String key() {
        
        return "story_hashtags";
    }

    @Override
    @SneakyThrows
    public String metadata() {
        
        return new ObjectMapper().writeValueAsString(this.map());
    }

    @Override
    public boolean check() throws IllegalArgumentException {
        if(tag_name == null) {
            throw new IllegalArgumentException("tag_name cannot be null");
        }
        if(x < 0 || x > 1 || y < 0 || y > 1 || z < 0 || z > 1) {
            throw new IllegalArgumentException("x y z is greater than 1.0 or less than 0.0");
        }
        if(width < 0 || width > 1 || height < 0 || height > 1) {
            throw new IllegalArgumentException("width height is greater than 1.0 or less than 0.0");
        }
        
        return true;
    }

}
