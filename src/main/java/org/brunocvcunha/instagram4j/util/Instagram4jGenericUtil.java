package org.brunocvcunha.instagram4j.util;

import com.eaio.uuid.UUID;

/**
 * Generic utils
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class Instagram4jGenericUtil {
    
    /**
     * Generate UUID
     * @param dash If needs to keep dash
     * @return UUID
     */
    public static String generateUuid(boolean dash) {
        String uuid = new UUID().toString();
        
        if (dash) {
            return uuid;
        }
        
        return uuid.replaceAll("-", "");
    }
}
