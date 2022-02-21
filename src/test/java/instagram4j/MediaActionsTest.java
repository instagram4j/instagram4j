package instagram4j;


import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.media.Media;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineVideoMedia;
import com.github.instagram4j.instagram4j.models.user.User;
import com.github.instagram4j.instagram4j.requests.feed.FeedClipsRequest;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedClipsResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import okhttp3.OkHttpClient;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MediaActionsTest {
    private static final String PATH = "temp/";
    private static final String CLIENT_FILE = PATH + "igclient.ser";
    private static final String COOKIE_FILE = PATH + "cookie.ser";
    private static final Logger LOGGER = Logger.getRootLogger();

    private static IGClient client;

    public static void setUpProxy(OkHttpClient.Builder clientBuilder) {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
        SSLContext trustAllSslContext;
        try {
            trustAllSslContext = SSLContext.getInstance("SSL");
            trustAllSslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
        SSLSocketFactory trustAllSslSocketFactory = trustAllSslContext.getSocketFactory();

        clientBuilder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(8080)));

    }

    @BeforeClass
    public static void setUp() throws IOException, ClassNotFoundException {
        BasicConfigurator.configure();
        LOGGER.setLevel(Level.ALL);
        OkHttpClient.Builder clientBuilder = IGUtils.defaultHttpClientBuilder();

        setUpProxy(clientBuilder);

        client = IGClient.deserialize(new File(CLIENT_FILE), new File(COOKIE_FILE), clientBuilder);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        client.serialize(new File(CLIENT_FILE), new File(COOKIE_FILE));
        LOGGER.info("Client has been serialized successfully");
    }

    @Test
    public void testClipUpload() {
        client.actions().clips()
                .uploadClip(new File(PATH + "test.mp4"),
                        new File(PATH + "test.jpg"),
                        "my caption")
                .join();
    }

    @Test
    public void testTimelineVideoUpload() {
        client.actions().timeline()
                .uploadVideo(new File(PATH + "test.mp4"),
                        new File(PATH + "test.jpg"),
                        "my caption")
                .join();
    }

    @Test
    public void testFeedUserTimeline() {
        User user = client.actions().users().findByUsername("cristiano").join().getUser();
        FeedUserResponse response = new FeedUserRequest(user.getPk()).execute(client).join();
        response.getItems()
                .forEach(media -> {
                    LOGGER.info(media.getMedia_type() + " " + media.getLike_count() + " " + media.getCaption().getText());
                });
    }

    @Test
    public void testFeedDiscoverClips() {
        CompletableFuture<FeedClipsResponse> responseCompletableFuture = client.actions().clips().feedClips(FeedClipsRequest.FeedType.DISCOVER);
        FeedClipsResponse response = responseCompletableFuture.join();
        LOGGER.warn(response.getItems().size());
        response.getItems()
                .forEach(media -> LOGGER.info(media.getId() + " " + media.getUser().getUsername() + " " + media.getVideo_versions().get(0).getUrl()));
    }

    @Test
    public void testFeedDiscoverClipsWithAmount() {
        int amount = 21;
        List<TimelineVideoMedia> clips = client.actions().clips().feedClipsList(FeedClipsRequest.FeedType.DISCOVER, amount);
        clips.forEach(media -> LOGGER.info(media.getId() + " " + media.getUser().getUsername()));
        Assert.assertTrue(clips.size() >= amount);
        float uniqueAmount = clips.stream().map(Media::getId).collect(Collectors.toSet()).size();
        LOGGER.info("Unique amount = " + uniqueAmount + "; uniqueness = " + uniqueAmount / clips.size());
    }

    @Test
    public void testFeedDiscoverClipsWithAmountAsync() {
        int amount = 21;
        List<TimelineVideoMedia> clips = client.actions().clips().feedClipsAsync(FeedClipsRequest.FeedType.DISCOVER, amount).join();
        clips.forEach(media -> LOGGER.info(media.getId() + " " + media.getUser().getUsername()));
        Assert.assertTrue(clips.size() >= amount);
        float uniqueAmount = clips.stream().map(Media::getId).collect(Collectors.toSet()).size();
        LOGGER.info("Unique amount = " + uniqueAmount + "; uniqueness = " + uniqueAmount / clips.size());
    }

    @Test
    public void testChangeBio() {
        client.actions().account().setBio("test").join();
    }
}
