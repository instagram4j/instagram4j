package com.jxinsta.web;

import com.jxinsta.web.endpoints.HashtagPost;
import com.jxinsta.web.endpoints.PublicAPIs;
import com.jxinsta.web.endpoints.post.PostData;
import com.jxinsta.web.endpoints.profile.ProfileData;
import com.jxinsta.web.paginators.HashtagPaginator;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PublicAPITest {
    private static final String TEST_POST_URL = "https://www.instagram.com/p/DWk2CHCkuLj/";
    private static final String TARGET_USERNAME = "instagram";

    @Test
    void testGetPostInfo() throws InstagramException, IOException {
        PostData postData = PublicAPIs.getPostInfo(TEST_POST_URL);
        assertNotNull(postData);
        assertNotNull(postData.id);
        System.out.println("Web Public Post Info ID: " + postData.id);
    }

    @Test
    void testGetProfileInfo() throws InstagramException {
        ProfileData profileData = PublicAPIs.getProfileInfo(TARGET_USERNAME);
        assertNotNull(profileData);
        assertEquals(TARGET_USERNAME, profileData.username);
        System.out.println("Web Public Profile Info Name: " + profileData.name);
    }

    @Test
    void testGetPosts() throws InstagramException {
        ProfileData profileData = PublicAPIs.getProfileInfo(TARGET_USERNAME);
        String pk = profileData.pk;
        
        List<PostData> posts = PublicAPIs.getPosts(pk, 5, null);
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        System.out.println("Web Public Posts retrieved: " + posts.size());
    }

    @Test
    @SuppressWarnings("deprecation")
    void testHashtagSearch() throws InstagramException {
        HashtagPaginator hashtagPaginator = PublicAPIs.hashtagSearch("iran", null);
        List<HashtagPost> posts = hashtagPaginator.next();
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        System.out.println("Web Public Hashtag Posts retrieved: " + posts.size());
    }
}
