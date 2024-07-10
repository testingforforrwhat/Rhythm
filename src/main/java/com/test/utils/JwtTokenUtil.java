package com.test.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private static final String SECRET_KEY = "u2J37FcFZrRiLmJKiFZLzOCN4KsoFN4OFJ6pE9HiBsc=";

    // 生成令牌
    public static String generateToken(String username) {
        System.out.println("SECRET_KEY: " + SECRET_KEY);
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 24min有效期
                .sign(algorithm);
    }

    // 解码令牌
    public static DecodedJWT decodeToken(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }

    // 验证令牌
    public static boolean validateToken(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject(username)
                    .build();
            verifier.verify(token);
            System.out.println("验证令牌成功");
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    // 从令牌中获取用户名
    public static String getUsernameFromToken(String token) {
        return decodeToken(token).getSubject();
    }
}