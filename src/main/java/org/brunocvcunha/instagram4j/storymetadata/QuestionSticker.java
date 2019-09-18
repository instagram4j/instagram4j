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

import lombok.Getter;
import lombok.Setter;

/**
 * Question Sticker
 * 
 * @author George Chousos 💛 gxousos@gmail.com
 *
 */
@Getter
@Setter
public class QuestionSticker {

	private String question_type;
	private long question_id;
	private String question;
	private long media_id;
	private String text_color;
	private String background_color;
	private Boolean viewer_can_interact;
	private String profile_pic_url;
	
}
