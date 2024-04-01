package edu.pnu.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtil {
	private static final long ACCESS_TOKEN_MSEC = 30 * (60 * 1000);
	private static final String JWT_KEY = "edu.pnu.jwt";
	private static final String claimName = "customerNum";
	private static final String prefix = "Bearer ";
	
	private static String getJWTSource (String token) {
		if (token.startsWith(prefix)) return token.replace(prefix, "");
		return token;
	}
	
	public static String getJWT(String username) {
	    long currentTimeMillis = System.currentTimeMillis();
	    long expirationTimeMillis = currentTimeMillis + ACCESS_TOKEN_MSEC;

	    String src = JWT.create()
	            .withClaim(claimName, username)
	            .withIssuedAt(new Date(currentTimeMillis)) // 토큰 발급 시간 설정
	            .withExpiresAt(new Date(expirationTimeMillis))
	            .sign(Algorithm.HMAC256(JWT_KEY));
	    return prefix + src;
	}


	public static String getClaim(String token) {
		String tok = getJWTSource(token);
		return JWT.require(Algorithm.HMAC256(JWT_KEY)).build().verify(tok).getClaim(claimName).asString();
	}
	
	public static boolean isExpired(String token) {
		String tok = getJWTSource(token);
		return JWT.require(Algorithm.HMAC256(JWT_KEY)).build().verify(tok).getExpiresAt().before(new Date());
	}
	
}
