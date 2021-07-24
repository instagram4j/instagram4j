package com.github.instagram4j.instagram4j.utils;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.instagram4j.instagram4j.IGConstants;
import lombok.SneakyThrows;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class IGUtils {
    private static final String BASE64URL_CHARMAP =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectWriter WRITER = MAPPER.writer();
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setSerializationInclusion(Include.NON_NULL);
    }

    private IGUtils() {}

    /**
     * The characters from a hex-string
     */
    public static final String XLATE = "0123456789abcdef";

    /**
     * Digest a string using the given codec and input
     * 
     * @param codec Codec to use
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
     * @param bytes byte array
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
     * Generate signed payload
     * 
     * @param payload Payload
     * @return Signed string
     */
    @SneakyThrows
    public static String generateSignature(String payload) {
        String parsedData = URLEncoder.encode(payload, "UTF-8");
        // new Instagram version no longer signs requests
        return "signed_body=SIGNATURE." + parsedData;

    }

    /**
     * Converts an Instagram ID to their shortcode system.
     *
     * @param code The ID to convert. Must be provided as a string if it's larger than the size of
     *        an integer, which MOST Instagram IDs are!
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
            encoded += BASE64URL_CHARMAP
                    .charAt(Integer.parseInt(base2.substring(6 * i, 6 * i + 6), 2));

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

    @SneakyThrows
    public static String encryptPassword(String password, String enc_id, String enc_pub_key) {
        byte[] rand_key = new byte[32], iv = new byte[12];
        SecureRandom sran = new SecureRandom();
        sran.nextBytes(rand_key);
        sran.nextBytes(iv);
        String time = String.valueOf(System.currentTimeMillis() / 1000);

        // Encrypt random key
        String decoded_pub_key =
                new String(Base64.getDecoder().decode(enc_pub_key), StandardCharsets.UTF_8)
                        .replaceAll("-(.*)-|\n", "");
        Cipher rsa_cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
        rsa_cipher.init(Cipher.ENCRYPT_MODE, KeyFactory.getInstance("RSA")
                .generatePublic(
                        new X509EncodedKeySpec(Base64.getDecoder().decode(decoded_pub_key))));
        byte[] rand_key_encrypted = rsa_cipher.doFinal(rand_key);

        // Encrypt password
        Cipher aes_gcm_cipher = Cipher.getInstance("AES/GCM/NoPadding");
        aes_gcm_cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(rand_key, "AES"),
                new GCMParameterSpec(128, iv));
        aes_gcm_cipher.updateAAD(time.getBytes());
        byte[] password_encrypted = aes_gcm_cipher.doFinal(password.getBytes());

        // Write to final byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(Integer.valueOf(1).byteValue());
        out.write(Integer.valueOf(enc_id).byteValue());
        out.write(iv);
        out.write(ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN)
                .putChar((char) rand_key_encrypted.length).array());
        out.write(rand_key_encrypted);
        out.write(Arrays.copyOfRange(password_encrypted, password_encrypted.length - 16,
                password_encrypted.length));
        out.write(Arrays.copyOfRange(password_encrypted, 0, password_encrypted.length - 16));

        return String.format("#PWD_INSTAGRAM:%s:%s:%s", "4", time,
                Base64.getEncoder().encodeToString(out.toByteArray()));
    }

    public static OkHttpClient.Builder defaultHttpClientBuilder() {
        return new OkHttpClient.Builder().cookieJar(new SerializableCookieJar());
    }

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    @SneakyThrows(JsonProcessingException.class)
    public static String objectToJson(Object obj) {
        return WRITER.writeValueAsString(obj);
    }

    public static <T> T jsonToObject(String json, Class<T> view) throws JsonProcessingException {
        return MAPPER.readValue(json, view);
    }

    public static <T> T convertToView(Object o, Class<T> view) {
        return MAPPER.convertValue(o, view);
    }

    public static <T> T convertToView(Object o, TypeReference<T> type) {
        return MAPPER.convertValue(o, type);
    }

    public static Optional<String> getCookieValue(CookieJar jar, String key) {
        return jar.loadForRequest(HttpUrl.get(IGConstants.BASE_API_URL)).stream()
                .filter(cookie -> cookie.name().equals(key)).map(Cookie::value).findAny();
    }

    public static String truncate(String s) {
        return s != null ? s.substring(0, Math.min(100, s.length())) : s;
    }

}
