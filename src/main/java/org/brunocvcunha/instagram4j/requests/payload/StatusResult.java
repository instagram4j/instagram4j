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
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Status Result
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class StatusResult {
    @NonNull
    private String status;
    private String message;
    
    private boolean spam;
    private boolean lock;
    private String feedback_title;
    private String feedback_message;
    private String error_type;
    private String checkpoint_url;
    
    public static void setValues(StatusResult to, StatusResult from) {
    	to.setStatus(from.getStatus());
    	to.setMessage(from.getMessage());
    	to.setSpam(from.isSpam());
    	to.setLock(from.isLock());
    	to.setFeedback_title(from.getFeedback_title());
    	to.setFeedback_message(from.getFeedback_message());
    	to.setError_type(from.getError_type());
    	to.setCheckpoint_url(from.getCheckpoint_url());
    }
}