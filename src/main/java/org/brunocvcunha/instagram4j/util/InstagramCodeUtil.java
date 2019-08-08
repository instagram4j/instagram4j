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
package org.brunocvcunha.instagram4j.util;

/**
 * Instagram Code Utils
 * @author WinSide (Mark Kunitsky)
 */
public class InstagramCodeUtil {

    private static final String BASE64URL_CHARMAP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    /**
     * Converts an Instagram ID to their shortcode system.
     *
     * @param code The ID to convert. Must be provided as a string if
     *             it's larger than the size of an integer, which MOST
     *             Instagram IDs are!
     * @return The shortcode.
     */
    public static String toCode(long code) {
        String base2 = Long.toBinaryString(code);
        if (base2.isEmpty())
            return "";

        int padAmount = (int) Math.ceil((double) base2.length() / 6);
        base2 = String.format("%" + padAmount * 6 + "s", base2).replace(' ', '0');

        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < padAmount; i++)
            encoded.append(BASE64URL_CHARMAP.charAt(Integer.parseInt(base2.substring(6 * i, 6 * i + 6))));

        return encoded.toString();
    }

    /**
     * Converts an Instagram shortcode to a numeric ID.
     *
     * @param code The shortcode.
     * @return The numeric ID.
     * @throws IllegalArgumentException If bad parameters are provided.
     */

    public static long fromCode(String code) throws IllegalArgumentException {
        if (code == null || code.matches("/[^A-Za-z0-9\\-_]/"))
            throw new IllegalArgumentException("Input must be a valid Instagram shortcode.");

        StringBuilder base2 = new StringBuilder();
        for (char c : code.toCharArray()) {
            int base64 = BASE64URL_CHARMAP.indexOf(c);
            base2.append(String.format("%6s", Integer.toBinaryString(base64)).replace(' ', '0'));
        }
        return Long.parseLong(base2.toString(), 2);
    }
}
