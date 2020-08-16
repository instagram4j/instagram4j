package instagram4j;

import org.junit.Assert;
import org.junit.Test;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import okhttp3.Request;

public class RequestTest {
    private class IGTestRequest extends IGRequest<IGResponse> {
        @Override
        public String path() {
            return "test/path/";
        }

        @Override
        public Request formRequest(IGClient client) {
            // dummy
            return null;
        }

        @Override
        public Class<IGResponse> getResponseType() {
            return IGResponse.class;
        }

    }

    @Test
    public void testUrlFormation() {
        IGTestRequest test = new IGTestRequest();
        Assert.assertEquals("https://i.instagram.com/api/v1/test/path/",
                test.formUrl(null).toString());
    }
}
