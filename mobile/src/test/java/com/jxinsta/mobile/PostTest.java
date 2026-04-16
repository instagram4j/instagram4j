package com.jxinsta.mobile;

import com.jxinsta.mobile.endpoints.post.Comment;
import com.jxinsta.mobile.endpoints.post.Post;
import com.jxinsta.mobile.endpoints.post.PostData;
import com.jxinsta.mobile.paginators.CommentPaginator;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostTest {
    private static JxInsta insta;
    private static final String AUTH_TOKEN = "PLACE_YOUR_AUTH_TOKEN";
    private static final String TEST_POST_URL = "https://www.instagram.com/p/DWg8NuZEj9p/"; // Example post

    @BeforeAll
    static void setup() {
        insta = JxInsta.getInstance(AUTH_TOKEN);
    }

    @Test
    @Order(1)
    void testGetPostByUrl() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        assertNotNull(post);
        assertNotNull(post.id);
        System.out.println("Post ID: " + post.id + " Caption: " + post.caption);
    }

    @Test
    @Order(2)
    void testLikeDislikePost() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        assertDoesNotThrow(post::like);
        System.out.println("Liked post");
        assertDoesNotThrow(post::dislike);
        System.out.println("Disliked post");
    }

    @Test
    @Order(3)
    void testCommentOnPost() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        String commentText = "Test comment from JxInsta JUnit " + System.currentTimeMillis();
        assertDoesNotThrow(() -> post.comment(commentText));
        System.out.println("Commented: " + commentText);
    }

    @Test
    @Order(4)
    void testGetCommentsAndLikeComment() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        CommentPaginator paginator = post.getComments();
        assertTrue(paginator.hasNext());
        List<Comment> comments = paginator.next();
        assertNotNull(comments);
        if (!comments.isEmpty()) {
            Comment firstComment = comments.get(0);
            assertDoesNotThrow(firstComment::like);
            System.out.println("Liked comment by: " + firstComment.username);
            assertDoesNotThrow(firstComment::dislike);
            System.out.println("Disliked comment");
        }
    }

    @Test
    @Order(5)
    void testLikers() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        List<String> likers = post.likers();
        assertNotNull(likers);
        System.out.println("Found " + likers.size() + " likers");
    }
}
