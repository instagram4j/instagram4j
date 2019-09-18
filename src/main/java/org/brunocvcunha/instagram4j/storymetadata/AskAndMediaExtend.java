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
import lombok.ToString;

/**
 * Ask And Media Extend
 * 
 * @author George Chousos 💛 gxousos@gmail.com
 *
 */
@Getter
@Setter
@ToString
public class AskAndMediaExtend {

    private double x;
    private double y;
    private double z;
    private double width;
    private double height;
    private double rotation;
    
    private int is_pinned; // byte would be better or boolean conversion? i am in hurry and i am not used to java 
    private int is_hidden;
    private int is_sticker;

}