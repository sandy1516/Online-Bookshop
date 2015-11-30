package com.san.pro.BookStore.util;

import com.auth0.jwt.JWTSigner;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

/**
 * Created by sandeepkumar.s on 11/11/2015.
 */
public class Cryptography {

    /**
     * Indicates the default log rounds for bcrypt to hash the password
     */
    private final static int DEFAULT_LOG_ROUNDS = 8;
    /**
     * Default algorithm to generate pseudo random byte data
     */
    private final static String DEFAULT_ALGORITHM = "SHA1PRNG";

    private final static Logger LOGGER = LoggerFactory.getLogger(Cryptography.class);

    private Cryptography() {

    }

    public static String encryptPassword(String password) throws IllegalArgumentException{
        if(password == null || password.trim().length()<1) {
            throw new IllegalArgumentException("password is invalid");
        }
        String hashend = null;
        try {
            hashend = BCrypt.hashpw(password, BCrypt.gensalt(DEFAULT_LOG_ROUNDS, SecureRandom.getInstance(DEFAULT_ALGORITHM)));
        } catch (NoSuchAlgorithmException nsae) {
            LOGGER.error("Unexpected Exception", nsae);
        }
        return hashend;
    }

    public static boolean validatePassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public static String signJwt(Map<String, Object> claims, String secret) {
        JWTSigner signer = new JWTSigner(secret);
        return signer.sign(claims);
    }
}
