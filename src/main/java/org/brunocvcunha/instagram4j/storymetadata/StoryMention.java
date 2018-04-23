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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.SneakyThrows;

/**
 * StoryMention
 * @author Justin Vo
 *
 * Note: Only makes area interactive. Does not apply 'sticker'; it has to be drawn manually!
 */
@Builder
public class StoryMention extends StoryMetadata {
    /**
     * Collection of ReelMention
     */
    private Collection<ReelMention> reel_mentions;
    
    private List<Object> map(){
        return reel_mentions.stream().map(m -> m.map()).collect(Collectors.toList());
    }
    
    @Override
    public String key() {
        return "reel_mentions";
    }

    @Override
    @SneakyThrows
    public String metadata() {
        return new ObjectMapper().writeValueAsString(this.map());
    }

    @Override
    public boolean check() throws IllegalArgumentException {
        
        return true;
    }

}
