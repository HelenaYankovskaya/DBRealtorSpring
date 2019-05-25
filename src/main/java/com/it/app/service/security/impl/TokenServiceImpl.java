package com.it.app.service.security.impl;

import com.it.app.security.model.AuthenticationUserDetails;
import com.it.app.service.security.TokenService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * The class represents a Implementation of TokenService
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String INVALID_JWT_SIGNATURE_MESSAGE = "Invalid JWT signature";

    private static final String INVALID_JWT_TOKEN_MESSAGE = "Invalid JWT token";

    private static final String EXPIRED_JWT_TOKEN_MESSAGE = "Expired JWT token";

    private static final String UNSUPPORTED_JWT_TOKEN_MESSAGE = "Unsupported JWT token";

    private static final String JWT_CLAIMS_STRING_IS_EMPTY_MESSAGE = "JWT claims string is empty";

    private static final String JWT_SECRET = "secret";

    private static final Integer JWT_EXPIRATION_MILLIS = 600000;

    /**
     * Generates JWT from Authentication object
     *
     * @param authentication - Authentication object created by authentication providers
     * @return - JWT
     */
    @Override
    public String generate(Authentication authentication) {
        return generate(((AuthenticationUserDetails) authentication.getPrincipal()).getUsername());
    }

    /**
     * Refreshes existing JWT
     *
     * @param token - JWT
     * @return - new JWT
     */
    @Override
    public String refresh(String token) {
        return generate(extractUsername(token));
    }

    /**
     * Extracts username from JWT
     *
     * @param token - JWT
     * @return - JWT subject value
     */
    @Override
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     * Validates incoming JWT by the special signing key used to verify any discovered JWS digital signature
     *
     * @param authToken - JWT for validation
     * @return - true, if validation was successful
     */
    @Override
    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error(INVALID_JWT_SIGNATURE_MESSAGE, e);
            throw e;
        } catch (MalformedJwtException e) {
            LOGGER.error(INVALID_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (ExpiredJwtException e) {
            LOGGER.error(EXPIRED_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (UnsupportedJwtException e) {
            LOGGER.error(UNSUPPORTED_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (IllegalArgumentException e) {
            LOGGER.error(JWT_CLAIMS_STRING_IS_EMPTY_MESSAGE, e);
            throw e;
        }
    }

    private String generate(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MILLIS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
}