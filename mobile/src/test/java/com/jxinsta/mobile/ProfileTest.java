package com.jxinsta.mobile;

import com.jxinsta.mobile.endpoints.profile.Profile;
import com.jxinsta.mobile.endpoints.profile.ProfileData;
import com.jxinsta.mobile.endpoints.post.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileTest {
    private static JxInsta insta;
    private static final String AUTH_TOKEN = "PLACE_YOUR_AUTH_TOKEN";
    private static final String TARGET_USERNAME = "instagram";

    @BeforeAll
    static void setup() {
        insta = JxInsta.getInstance(AUTH_TOKEN);
    }

    @Test
    @Order(1)
    void testGetProfile() throws InstagramException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        assertNotNull(profile);
        assertEquals(TARGET_USERNAME, profile.username);
        assertNotNull(profile.pk);
        System.out.println("Profile: " + profile);
    }

    @Test
    @Order(2)
    void testProfilePosts() throws InstagramException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        var paginator = profile.getPosts();
        assertTrue(paginator.hasNext());
        List<Post> posts = paginator.next();
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        System.out.println("First post caption: " + posts.get(0).caption);
    }

    @Test
    @Order(3)
    void testFollowUnfollow() throws InstagramException {
        // Caution: Real API calls
        Profile profile = insta.getProfile(TARGET_USERNAME);
        
        // Test follow
        assertDoesNotThrow(profile::follow);
        System.out.println("Followed " + TARGET_USERNAME);
        
        // Test unfollow
        assertDoesNotThrow(profile::unfollow);
        System.out.println("Unfollowed " + TARGET_USERNAME);
    }

    @Test
    @Order(4)
    void testFollowers() throws InstagramException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        var followersPaginator = profile.getFollowers();
        if (followersPaginator.hasNext()) {
            List<Profile> followers = followersPaginator.next();
            assertNotNull(followers);
            System.out.println("Found " + followers.size() + " followers");
        }
    }

    @Test
    @Order(5)
    void testFollowing() throws InstagramException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        var followingPaginator = profile.getFollowings();
        if (followingPaginator.hasNext()) {
            List<Profile> following = followingPaginator.next();
            assertNotNull(following);
            System.out.println("Found " + following.size() + " following");
        }
    }

    @Test
    @Order(6)
    void testGetStoryAndHighlights() throws InstagramException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        
        try {
            var stories = profile.getStory();
            assertNotNull(stories);
            System.out.println("Stories count: " + stories.size());
        } catch (Exception e) {
            System.err.println("Could not fetch stories: " + e.getMessage());
        }

        try {
            var highlights = profile.getHighlights();
            assertNotNull(highlights);
            System.out.println("Highlights count: " + highlights.size());
        } catch (Exception e) {
            System.err.println("Could not fetch highlights: " + e.getMessage());
        }
    }

    @Test
    @Order(7)
    void testSearchProfile() throws InstagramException {
        List<ProfileData> results = insta.search(TARGET_USERNAME);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        boolean found = results.stream().anyMatch(p -> p.username.equalsIgnoreCase(TARGET_USERNAME));
        assertTrue(found, "Target username not found in search results");
        System.out.println("Search results count: " + results.size());
    }
}
