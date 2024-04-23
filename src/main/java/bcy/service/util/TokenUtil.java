package bcy.service.util;

import java.util.Calendar;
import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenUtil {
    private static final String SECRET = "banciyuan123";

    public static String generateToken(Long id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 48);

        return JWT.create()
                .withHeader(new HashMap<>()) // Header
                .withClaim("uid", id) // Payload
                .withExpiresAt(calendar.getTime()) // 过期时间
                .sign(Algorithm.HMAC256(SECRET)); // 签名用的secret
    }

    public static Long parseToken(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            return Long.valueOf(jwt.getClaim("uid").toString());
        } catch (TokenExpiredException e) {
            throw new ConditionException(555, "token过期！");
        } catch (Exception e) {
            throw new ConditionException(555, e.getMessage());
        }
    }
}
