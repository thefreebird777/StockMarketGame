package app.services;

import app.exceptions.APIException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class LoginService {
    
    public LoginService() { }
    
    /**
     * Encodes a JWT token for the user's browser cache
     * @return - JWT token
     */
    public static String generateJWT() throws APIException {
        String token = "";
        try {
            long nowMillis = System.currentTimeMillis();
            long expTime = nowMillis + 1200000; //1,200,000ms = 20min
            Date exp = new Date(expTime);            
            
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                .withExpiresAt(exp)
                .withIssuer("auth0")
                .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception){
            throw new APIException(500, exception.getMessage(), "");
        } catch (JWTCreationException exception){
            throw new APIException(500, exception.getMessage(), "");
        }
    }
    
    /**
     * Decodes and verifies if a JWT is still valid
     * @param token - JWT token
     * @return - True/False
     */
    public static boolean verifyJWT(String token) {       
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException exception){
            return false;
        } catch (JWTVerificationException exception){
            return false;
        }        
    }
}