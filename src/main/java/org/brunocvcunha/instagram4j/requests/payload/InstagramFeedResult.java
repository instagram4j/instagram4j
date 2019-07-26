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
 * Data class for response from feed requests
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramFeedResult extends StatusResult {
    private boolean auto_load_more_enabled;
    private int num_results;
    private String next_max_id;
    private List<InstagramFeedItem> items;
    private boolean more_available;
}
