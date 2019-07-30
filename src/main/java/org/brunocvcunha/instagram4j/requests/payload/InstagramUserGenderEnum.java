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

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Kohonovsky
 * @since 2019-04-28
 */
@RequiredArgsConstructor
public enum InstagramUserGenderEnum {

    MALE("1"),
    FEMALE("2"),
    NOT_SPECIFIED("3");

    private static Map<String, InstagramUserGenderEnum> gendersMap = new HashMap<>(3);

    static {
        gendersMap.put(MALE.getInstagramCode(), MALE);
        gendersMap.put(FEMALE.getInstagramCode(), FEMALE);
        gendersMap.put(NOT_SPECIFIED.getInstagramCode(), NOT_SPECIFIED);
    }

    @Getter
    private final String instagramCode;

    /**
     * @param value - instagram gender code or enum value
     * @return enum
     */
    @JsonCreator
    public static InstagramUserGenderEnum forValue(String value) {
        InstagramUserGenderEnum resultEnum = gendersMap.get(StringUtils.lowerCase(value));
        if (resultEnum == null) {
            resultEnum = InstagramUserGenderEnum.valueOf(value);
        }
        return resultEnum;
    }

}


