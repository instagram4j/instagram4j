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

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * StoryPollItem
 * @author Justin Vo
 *
 */
@Getter
@Setter
public class StoryPollItem extends StoryItem {
    private PollStickerItem poll_sticker;
    private boolean viewer_can_vote;
    private boolean is_shared_result;
    private boolean finished;
    
    @Getter
    @Setter
    public static class PollStickerItem {
	private String id;
	private long poll_id;
	private String question;
	private List<TallyItem> tallies;
	
	@Getter
	@Setter
	public static class TallyItem {
	    private String text;
	    private int count;
	    private int font_size;
	}
    }
}
