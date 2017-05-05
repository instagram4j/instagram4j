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
 * Response from media comments request
 *
 * Evgeny Bondarenko (evgbondarenko@gmail.com)
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramGetMediaCommentsResult extends StatusResult {
	private boolean comment_likes_enabled;
	private int comment_count;
	private boolean caption_is_edited;
	private boolean has_more_comments;
	private boolean has_more_headload_comments;
	private String next_max_id;
	private List<InstagramComment> comments;
}
