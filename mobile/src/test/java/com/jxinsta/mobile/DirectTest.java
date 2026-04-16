package com.jxinsta.mobile;

import com.jxinsta.mobile.endpoints.direct.Inbox;
import com.jxinsta.mobile.endpoints.direct.Message;
import com.jxinsta.mobile.endpoints.direct.Thread;
import com.jxinsta.mobile.paginators.MessagePaginator;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DirectTest {
    private static JxInsta insta;
    private static final String AUTH_TOKEN = "PLACE_YOUR_AUTH_TOKEN";
    @BeforeAll
    static void setup() {
        insta = JxInsta.getInstance(AUTH_TOKEN);
    }

    @Test
    @Order(1)
    void testGetInbox() throws InstagramException {
        Inbox inbox = insta.getDirectInbox(10, 5);
        assertNotNull(inbox);
        assertNotNull(inbox.threads);
        System.out.println("Inbox total threads: " + inbox.totalThreads);
        if (!inbox.threads.isEmpty()) {
            System.out.println("First thread recipient: " + inbox.threads.getFirst().recipient);
        }
    }

    @Test
    @Order(2)
    void testGetThreadAndMessages() throws InstagramException {
        Inbox inbox = insta.getDirectInbox(5, 5);
        if (!inbox.threads.isEmpty()) {
            String threadId = inbox.threads.get(0).id;
            Thread thread = inbox.getThread(threadId, 10);
            assertNotNull(thread);
            assertEquals(threadId, thread.id);

            MessagePaginator messagePaginator = thread.getMessages();
            if (messagePaginator.hasNext()) {
                List<Message> messages = messagePaginator.next();
                assertNotNull(messages);
                System.out.println("Retrieved " + messages.size() + " messages from thread");
            }
        }
    }

    @Test
    @Order(3)
    void testSendMessage() throws InstagramException {
        Inbox inbox = insta.getDirectInbox(1, 1);
        if (!inbox.threads.isEmpty()) {
            Thread thread = inbox.threads.get(0);
            assertDoesNotThrow(() -> thread.sendMessage("Test message from JxInsta JUnit"));
            System.out.println("Sent test message to " + thread.recipient);
        }
    }

    @Test
    @Order(4)
    void testMarkSeen() throws InstagramException {
        Inbox inbox = insta.getDirectInbox(1, 1);
        if (!inbox.threads.isEmpty()) {
            Thread thread = inbox.threads.getFirst();
            assertDoesNotThrow(thread::markSeen);
            System.out.println("Marked thread as seen: " + thread.id);
        }
    }

    @Test
    @Order(5)
    void testGetRequests() throws InstagramException {
        Inbox inbox = insta.getDirectInbox(1, 1);
        List<Thread> requests = inbox.getRequests(5, 5);
        assertNotNull(requests);
        System.out.println("Pending requests count: " + requests.size());
    }
}
