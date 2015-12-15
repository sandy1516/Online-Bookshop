package com.san.pro.BookStore.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.exceptions.ErrorCodes;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SignatureException;
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

    public static String encryptPassword(String password) throws ApiException {
        if(password == null || password.trim().length()<1) {
            throw new ApiException(new IllegalArgumentException("password is invalid"), Response.Status.UNAUTHORIZED).addError(ErrorCodes.INVALID_PASSWORD);
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

    public static Map<String, Object> unsignedJwt(String token ,String secret, String audience) throws ApiException {
        try {
            return new JWTVerifier(secret, audience).verify(token);
        } catch(SignatureException e) {
            throw new ApiException(new SignatureException(" User is Unauthorized "), Response.Status.UNAUTHORIZED).addError(ErrorCodes.UNAUTHORIZED);
        } catch(NoSuchAlgorithmException | JWTVerifyException | InvalidKeyException | IOException ex) {
            throw new ApiException(ex, Response.Status.UNAUTHORIZED).addError(ErrorCodes.INVALID_AUTHORIZATION_TOKEN);
        }
    }
}

