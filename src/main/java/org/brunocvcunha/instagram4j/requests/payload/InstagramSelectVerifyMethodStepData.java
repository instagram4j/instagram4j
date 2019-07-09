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
 * InstagramSelectVerifyMethodStepData
 *
 * @author evosystem
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramSelectVerifyMethodStepData {

    public String security_code;
    public int sms_resend_delay;
    public int resend_delay;
    public String contact_point;
    public String form_type;
    public String phone_number_preview;
}