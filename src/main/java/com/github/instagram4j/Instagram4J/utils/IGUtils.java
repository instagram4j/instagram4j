package com.github.instagram4j.Instagram4J.utils;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.instagram4j.Instagram4J.IGConstants;

import lombok.SneakyThrows;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class IGUtils {
    private static final String BASE64URL_CHARMAP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final ObjectReader READER = MAPPER.reader();
    public static final ObjectWriter WRITER = MAPPER.writer();
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * The characters from a hex-string
     */
    public static final String XLATE = "0123456789abcdef";

    /**
     * Digest a string using the given codec and input
     * 
     * @param codec  Codec to use
     * @param source Source to use
     * @return
     */
    protected static String digest(String codec, String source) {
        try {
            MessageDigest digest = MessageDigest.getInstance(codec);
            byte[] digestBytes = digest.digest(source.getBytes(Charset.forName("UTF-8")));
            return hexlate(digestBytes, digestBytes.length);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(codec + " codec not available");
        }
    }

    /**
     * Get the MD5 (in hexadecimal presentation) for the given source
     * 
     * @param source The string to hash
     * @return MD5 hex presentation
     */
    public static String md5hex(String source) {
        return digest("MD5", source);
    }

    /**
     * Convert the byte array to a hexadecimal presentation (String)
     * 
     * @param bytes        byte array
     * @param initialCount count (length) of the input
     * @return
     */
    protected static String hexlate(byte[] bytes, int initialCount) {
        if (bytes == null) {
            return "";
        }

        int count = Math.min(initialCount, bytes.length);
        char[] chars = new char[count * 2];

        for (int i = 0; i < count; ++i) {
            int val = bytes[i];
            if (val < 0) {
                val += 256;
            }
            chars[(2 * i)] = XLATE.charAt(val / 16);
            chars[(2 * i + 1)] = XLATE.charAt(val % 16);
        }

        return new String(chars);
    }

    /**
     * Generates Instagram Device ID
     * 
     * @param username Username to generate
     * @param password Password to generate
     * @return device id
     */
    public static String generateDeviceId(String username, String password) {
        String seed = md5hex(username + password);
        String volatileSeed = "12345";

        return "android-" + md5hex(seed + volatileSeed).substring(0, 16);
    }

    /**
     * Generate a Hmac SHA-256 hash
     * 
     * @param key    key
     * @param string value
     * @return hashed
     */
    public static String generateHash(String key, String string) {
        SecretKeySpec object = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init((Key) object);
            byte[] byteArray = mac.doFinal(string.getBytes("UTF-8"));
            return new String(new Hex().encode(byteArray), "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generate signed payload
     * 
     * @param payload Payload
     * @return Signed string
     */
    @SneakyThrows
    public static String generateSignature(String payload) {
        String parsedData = URLEncoder.encode(payload, "UTF-8");

        String signedBody = "SIGNATURE";

        return "ig_sig_key_version=" + IGConstants.API_KEY_VERSION + "&signed_body=" + signedBody + '.' + parsedData;

    }

    /**
     * Converts an Instagram ID to their shortcode system.
     *
     * @param code The ID to convert. Must be provided as a string if
     *             it's larger than the size of an integer, which MOST
     *             Instagram IDs are!
     * @return The shortcode.
     */
    public static String toCode(long code) {
        String base2 = Long.toBinaryString(code);
        if (base2.isEmpty())
            return "";

        int padAmount = (int) Math.ceil((double) base2.length() / 6);
        base2 = String.format("%" + padAmount * 6 + "s", base2).replace(' ', '0');

        String encoded = "";
        for (int i = 0; i < padAmount; i++)
            encoded += BASE64URL_CHARMAP.charAt(Integer.parseInt(base2.substring(6 * i, 6 * i + 6), 2));

        return encoded;
    }

    /**
     * Converts an Instagram shortcode to a numeric ID.
     *
     * @param code The shortcode.
     * @return The numeric ID.
     * @throws IllegalArgumentException If bad parameters are provided.
     */
    public static long fromCode(String code) throws IllegalArgumentException {
        if (code == null || code.matches("/[^A-Za-z0-9\\-_]/"))
            throw new IllegalArgumentException("Input must be a valid Instagram shortcode.");

        String base2 = "";
        for (char c : code.toCharArray()) {
            int base64 = BASE64URL_CHARMAP.indexOf(c);
            base2 += String.format("%6s", Integer.toBinaryString(base64)).replace(' ', '0');
        }
        return Long.parseLong(base2, 2);
    }

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    @SneakyThrows
    public static String objectToJson(Object obj) {
        return WRITER.writeValueAsString(obj);
    }

    public static Optional<String> getCookieValue(CookieJar jar, String key) {
        return jar.loadForRequest(HttpUrl.get(IGConstants.BASE_API_URL)).stream()
                .filter(cookie -> cookie.name().equals(key)).map(Cookie::value).findAny();
    }
}
