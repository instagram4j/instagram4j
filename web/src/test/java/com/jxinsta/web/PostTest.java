package com.jxinsta.web;

import com.jxinsta.web.endpoints.post.Comment;
import com.jxinsta.web.endpoints.post.Post;
import com.jxinsta.web.paginators.CommentPaginator;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostTest {
    private static JxInsta insta;
    private static final String SESSION_ID = "58236085766%3AQAnd5EVJUQ9ubk%3A19%3AAYikSWnD7S-CM2wB6rRk-pS95O-I14FhpgbK_zNNkg";
    private static final String CSRF_TOKEN = "rgG8nBfSTOWf8LradZvCi5QeQzKjh0ib";
    private static final String TEST_POST_URL = "https://www.instagram.com/p/DWk2CHCkuLj/";

    @BeforeAll
    static void setup() throws InstagramException, IOException {
        insta = JxInsta.getInstance(SESSION_ID, CSRF_TOKEN);
    }

    @Test
    @Order(1)
    void testGetPostByUrl() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        assertNotNull(post);
        assertNotNull(post.id);
        System.out.println("Web Post ID: " + post.id);
    }

    @Test
    @Order(2)
    void testLikeDislikePost() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        assertDoesNotThrow(post::like);
        System.out.println("Liked post via web");
        assertDoesNotThrow(post::dislike);
        System.out.println("Disliked post via web");
    }

    @Test
    @Order(3)
    void testCommentOnPost() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        String commentText = "Web test comment " + System.currentTimeMillis();
        assertDoesNotThrow(() -> post.comment(commentText));
        System.out.println("Commented via web: " + commentText);
    }

    @Test
    @Order(4)
    void testGetComments() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        CommentPaginator paginator = post.getComments();
        assertTrue(paginator.hasNext());
        List<Comment> comments = paginator.next();
        assertNotNull(comments);
        System.out.println("Found " + comments.size() + " comments via web");
        if (!comments.isEmpty()) {
            Comment first = comments.get(0);
            System.out.println("First comment by: " + first.username + " text: " + first.text);
        }
    }

    @Test
    @Order(5)
    void testLikers() throws InstagramException, IOException {
        Post post = insta.getPost(TEST_POST_URL);
        List<String> likers = post.likers();
        assertNotNull(likers);
        System.out.println("Found " + likers.size() + " likers via web");
    }
}
