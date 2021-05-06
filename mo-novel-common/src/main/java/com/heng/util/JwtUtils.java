package com.heng.util;

/*
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
*/

public class JwtUtils {
    public static final String CLAIM_LOGIN_NAME = "loginName";
    public static final String CLAIM_ID = "userId";
    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 60*60*1000;

/*    *//**
     * token私钥
     *//*
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    *//**
     * 生成签名,15分钟后过期
     * @param username
     * @param userId
     * @return
     *//*
    public static String sign(String username,String userId){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim(CLAIM_LOGIN_NAME,username).withClaim(CLAIM_ID,userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static String sign(String username){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim(CLAIM_LOGIN_NAME,username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    *//**
     * 取出负载
     * @param token
     * @param claimName payload-key
     * @return payload-value
     *//*
    public static String verityToken(String token, String claimName){
        String urId = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            // 取出
            urId = jwt.getClaim(claimName).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urId;
    }

    *//**
     * 校验
     * @param token
     * @return
     *//*
    public static boolean verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }*/
}
