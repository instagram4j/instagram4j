package serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class SerializableCookieJar implements CookieJar, Serializable {

    private static final long serialVersionUID = -837498359387593793l;

    Map<String, List<SerializableCookie>> map = new HashMap<>();

    @Override
    public List<Cookie> loadForRequest(HttpUrl arg0) {
        return map.getOrDefault(arg0.host(), new ArrayList<SerializableCookie>()).stream()
                .map(c -> c.cookie)
                .collect(Collectors.toList());
    }

    @Override
    public void saveFromResponse(HttpUrl arg0, List<Cookie> arg1) {
        List<SerializableCookie> list =
                arg1.stream().map(c -> new SerializableCookie(c)).collect(Collectors.toList());
        if (map.putIfAbsent(arg0.host(), list) != null) {
            map.get(arg0.host()).addAll(list);
        }
    }

    @AllArgsConstructor
    public static class SerializableCookie implements Serializable {

        private static final long serialVersionUID = -8594045714036645534L;

        private transient Cookie cookie;

        private static long NON_VALID_EXPIRES_AT = -1L;

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(cookie.name());
            out.writeObject(cookie.value());
            out.writeLong(cookie.persistent() ? cookie.expiresAt() : NON_VALID_EXPIRES_AT);
            out.writeObject(cookie.domain());
            out.writeObject(cookie.path());
            out.writeBoolean(cookie.secure());
            out.writeBoolean(cookie.httpOnly());
            out.writeBoolean(cookie.hostOnly());
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            Cookie.Builder builder = new Cookie.Builder();

            builder.name((String) in.readObject());

            builder.value((String) in.readObject());

            long expiresAt = in.readLong();
            if (expiresAt != NON_VALID_EXPIRES_AT) {
                builder.expiresAt(expiresAt);
            }

            final String domain = (String) in.readObject();
            builder.domain(domain);

            builder.path((String) in.readObject());

            if (in.readBoolean())
                builder.secure();

            if (in.readBoolean())
                builder.httpOnly();

            if (in.readBoolean())
                builder.hostOnlyDomain(domain);

            cookie = builder.build();
        }

    }

}
