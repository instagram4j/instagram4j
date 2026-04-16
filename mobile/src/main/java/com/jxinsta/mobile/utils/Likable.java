package com.jxinsta.mobile.utils;

import com.jxinsta.mobile.InstagramException;

/**
 * Interface for objects that can be liked or disliked on Instagram.
 * Implementing classes should provide the logic for performing these actions via the API.
 */
public interface Likable {
    /**
     * Likes the item.
     *
     * @throws InstagramException If the API returns an error.
     */
    void like() throws InstagramException;

    /**
     * Removes the like from the item.
     *
     * @throws InstagramException If the API returns an error.
     */
    void dislike() throws InstagramException;
}
