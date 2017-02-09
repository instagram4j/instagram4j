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
import java.util.Map;

import lombok.Data;

/**
 * Upload Video Result
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Data
public class InstagramUploadVideoResult {
    private String status;
    private String message;
    private String upload_id;
    private List<Map<String, Object>> video_upload_urls;

}