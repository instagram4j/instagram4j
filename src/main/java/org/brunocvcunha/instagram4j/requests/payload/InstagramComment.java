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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Evgeny Bondarenko (evgbondarenko@gmail.com)
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramComment {
	private String pk;
	private long user_id;
	private String text;
	private int type;
	private long created_at;
	private long created_at_utc;
	private String content_type;
	private String status;
	private int bit_flags;
	private InstagramUser user;
	private boolean did_report_as_spam;
	private boolean share_enabled;
	private long media_id;
}
