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

@Builder
public class StorySlider extends StoryMetadata {
    /**
     * width 0.0 - 1.0
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
     * Question to display
     */
    private String question;
    /**
     * Emoji
     */
    @Builder.Default private String emoji = "\uD83D\uDE0D";
    /**
     * Color code in HEX
     */
    @Builder.Default private String text_color = "#7F007F";
    /**
     * Color code in HEX
     */
    @Builder.Default private String background_color = "#FFFFFF";
    /**
     * Unmodifiable
     */
    private final boolean is_pinned = false;
    private final boolean is_hidden = false;
    
    @Override
    public String key() {
        // TODO Auto-generated method stub
        return "story_sliders";
    }
    
    public List<Object> map(){
        Map<String, Object> storyslider = new HashMap<>();
        
        storyslider.put("x", x);
        storyslider.put("y", y);
        storyslider.put("z", z);
        storyslider.put("rotation", rotation);
        storyslider.put("height", height);
        storyslider.put("width", width);
        storyslider.put("is_pinned", is_pinned);
        storyslider.put("is_hidden", is_hidden);
        storyslider.put("text_color", text_color);
        storyslider.put("emoji", emoji);
        storyslider.put("question", question);
        storyslider.put("background_color", background_color);
        
        return Arrays.asList(storyslider);
    }
    
    @Override
    @SneakyThrows
    public String metadata() {
        // TODO Auto-generated method stub
        return new ObjectMapper().writeValueAsString(this.map());
    }

    @Override
    public boolean check() throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return true;
    }

}
