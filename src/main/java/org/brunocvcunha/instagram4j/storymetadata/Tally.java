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

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Tally
 * @author Justin Vo
 *
 */
@Builder
@Getter
@Setter
public class Tally {
    /**
     * String to be displayed on tally
     */
    private String text;
    /**
     * This cannot be modified.
     */
    private final int count = 0;
    /**
     * Font size of text. Range from 17.5 to 35.0
     */
    @Builder.Default private double font_size = 35;
    /**
     * Predefined tallies YES and NO
     */
    public static Tally YES = Tally.builder().text("YES").build(), NO = Tally.builder().text("NO").build();
    
    public Map<String, Object> map(){
        Map<String, Object> map = new LinkedHashMap<>();
        
        map.put("count", count);
        map.put("text", text);
        map.put("font_size", font_size > 35 ? 35 : font_size < 17.5 ? 17.5 : font_size);
        
        return map;
    }
}
