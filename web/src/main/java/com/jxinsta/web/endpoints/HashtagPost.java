package com.jxinsta.web.endpoints;

public record HashtagPost(
        String shortcode,
        String id,
        String caption,
        String username,
        String displayPicture,
        long views,
        String downloadUrl
) {
}
