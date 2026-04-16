package com.jxinsta.web;

import com.jxinsta.web.endpoints.profile.Profile;
import com.jxinsta.web.endpoints.profile.Story;
import com.jxinsta.web.endpoints.post.Post;
import com.jxinsta.web.paginators.PostPaginator;
import com.jxinsta.web.paginators.ProfilePaginator;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileTest {
    private static JxInsta insta;
    private static final String SESSION_ID = "38835725265%3A2l6ZGcAasRkiDW%3A12%3AAYgg_7WX_DW509oqFD9QLH6BDPXAJfpRIbzzhiMlmA";
    private static final String CSRF_TOKEN = "OfRPaKYM2RAuW4GQVgqINvNXnyVMW4jJ";
    private static final String TARGET_USERNAME = "therock";

    @BeforeAll
    static void setup() {
        insta = JxInsta.getInstance(SESSION_ID, CSRF_TOKEN);
    }

    @Test
    @Order(1)
    void testGetProfile() throws InstagramException, IOException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        assertNotNull(profile);
        assertEquals(TARGET_USERNAME, profile.username);
        assertNotNull(profile.pk);
        System.out.println("Web Profile: " + profile.name);
    }

    @Test
    @Order(2)
    void testProfilePosts() throws InstagramException, IOException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        PostPaginator paginator = profile.getPosts(null);
        assertTrue(paginator.hasNext());
        List<Post> posts = paginator.next();
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        System.out.println("First web post caption: " + posts.get(0).caption);
    }

    @Test
    @Order(3)
    void testFollowUnfollow() throws InstagramException, IOException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        
        assertDoesNotThrow(profile::follow);
        System.out.println("Followed " + TARGET_USERNAME);
        
        assertDoesNotThrow(profile::unfollow);
        System.out.println("Unfollowed " + TARGET_USERNAME);
    }

    @Test
    @Order(4)
    void testFollowers() throws InstagramException, IOException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        ProfilePaginator paginator = profile.getFollowers(null);
        assertTrue(paginator.hasNext());
        List<Profile> followers = paginator.next();
        assertNotNull(followers);
        System.out.println("Found " + followers.size() + " followers via web");
    }

    @Test
    @Order(5)
    void testFollowing() throws InstagramException, IOException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        ProfilePaginator paginator = profile.getFollowings(null);
        assertTrue(paginator.hasNext());
        List<Profile> following = paginator.next();
        assertNotNull(following);
        System.out.println("Found " + following.size() + " following via web");
    }

    @Test
    @Order(6)
    void testGetStoryAndHighlights() throws InstagramException, IOException {
        Profile profile = insta.getProfile(TARGET_USERNAME);
        
        try {
            List<Story> stories = profile.getStory();
            System.out.println("Web Stories count: " + (stories != null ? stories.size() : 0));
        } catch (Exception e) {
            System.err.println("Web story fetch failed: " + e.getMessage());
        }

        try {
            List<Story> highlights = profile.getHighlights();
            System.out.println("Web Highlights count: " + (highlights != null ? highlights.size() : 0));
        } catch (Exception e) {
            System.err.println("Web highlights fetch failed: " + e.getMessage());
        }
    }
}
