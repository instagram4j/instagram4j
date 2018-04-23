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

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;

/**
 * ReelMention
 * @author Justin Vo
 *
 */
@Builder
public class ReelMention {
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
     * user id (pk)
     */
    private String user_id;
    /**
     * height 0.0 - 1.0
     */
    @Builder.Default private double height = 1.0;
    /**
     * width 0.0 - 1.0
     */
    @Builder.Default private double width = 1.0;
    /**
     * unmodifiable values
     */
    private final boolean is_pinned = false;
    
    public Map<String, Object> map(){
        Map<String, Object> mapped = new HashMap<>();
        
        mapped.put("x", x);
        mapped.put("y", y);
        mapped.put("z", z);
        mapped.put("rotation", rotation);
        mapped.put("user_id", user_id);
        mapped.put("height", height);
        mapped.put("width", width);
        mapped.put("is_pinned", is_pinned);
        return mapped;
    }
}
