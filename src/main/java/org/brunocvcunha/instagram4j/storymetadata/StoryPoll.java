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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StoryPoll
 * @author Justin Vo
 *
 */
@Builder
public class StoryPoll extends StoryMetadata {
    /**
     * Question in StoryPoll (Note: You must manually draw the question on the image. It will not appear)
     */
    private String question;
    /**
     * List of tallies. Only two tallies per story poll
     */
    private List<Tally> tallies;
    /**
     * x position of poll sticker. Range from 0.0 to 1.0. 0.5 is center
     */
    @Builder.Default private double x = 0.5;
    /**
     * y position of poll sticker. Range from 0.0 to 1.0. 0.5 is center
     */
    @Builder.Default private double y = 0.5;
    /**
     * z position of poll sticker.
     */
    @Builder.Default private double z = 0;
    /**
     * width of poll sticker. Range from 0 to 1.0
     */
    @Builder.Default private double width = 1.0;
    /**
     * height of poll sticker. Range from 0 to 1.0
     */
    @Builder.Default private double height = 1.0;
    /**
     * Rotation of poll sticker
     */
    @Builder.Default private double rotation = 0;
    /**
     * These values are constants and cannot be modified.
     */
    private final String poll_id = "polling_sticker_vibrant";
    private final boolean is_pinned = false;
    private final boolean is_shared_result = false;
    
    private List<Object> map() {
        Map<String, Object> story_poll = new LinkedHashMap<>();
        
        story_poll.put("x", x);
        story_poll.put("height", height);
        story_poll.put("rotation", rotation);
        story_poll.put("y", y);
        story_poll.put("tallies", tallies.stream().map(t -> t.map()).collect(Collectors.toList()));
        story_poll.put("width", width);
        story_poll.put("z", z);
        story_poll.put("is_pinned", is_pinned);
        story_poll.put("question", question);
        story_poll.put("poll_id", poll_id);
        story_poll.put("is_shared_result", is_shared_result);
        
        return Arrays.asList(story_poll);
    }

    @Override
    public String key() {
        return "story_polls";
    }

    @Override
    @SneakyThrows
    public String metadata() {
        return new ObjectMapper().writeValueAsString(this.map());
    }
    
    @Override
    public boolean check() {
        if(tallies.size() != 2) {
            throw new IllegalArgumentException("Only two story poll tallies allowed");
        }
        if(question == null) {
            throw new IllegalArgumentException("Story poll question is null");
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
