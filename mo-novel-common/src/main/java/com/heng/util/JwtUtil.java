package com.heng.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * @author LJohn
 * @since 2021-05-06
 */
public class JwtUtil {
    public static final String CLAIMS_KEY_USERNAME = "mo_username";
    public static final String CLAIMS_KEY_USERID = "mo_userid";


    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static Long EXPIRE_TIME = 60 * 60 * 1000L;

    /**
     * token私钥
     */
    private static String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    /**
     * 根据用户信息生成token
     *
     * @param username
     * @return
     */
    public static String generateToken(String username)
    {
/*        HashMap<String, Object> clamins = new HashMap<>();
        clamins.put(CLAIMS_MY, username);
        clamins.put("CREATE_TIME", EXPIRE_TIME);*/

        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
//                .setClaims(clamins)
                .setSubject(username)
                .compact();
    }

    /**
     * 根据用户信息生成token
     *
     * @param username
     * @return
     */
    public static String generateToken(String username, Long id)
    {
        HashMap<String, Object> clamins = new HashMap<>();
        clamins.put(CLAIMS_KEY_USERID, id);

        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .setClaims(clamins)
                .setSubject(username)
                .compact();
    }


    /**
     * 从TOKEN从获取Clamins的username
     *
     * @param token
     * @return 用户信息
     */
    public static String getUsernameFromToken(String token)
    {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * 从JWT中获取Claim
     *
     * @param token
     * @param claim_key
     * @return
     */
    public static Object getClaim(String token, String claim_key)
    {
        return getClaimsFromToken(token).get(claim_key);
    }


    /**
     * 从token中获取JWT中的负载
     *
     * @param token
     * @return
     */
    @SneakyThrows
    private static Claims getClaimsFromToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从请求头中获取TOKEN
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request)
    {
        String token = request.getHeader("X-TOKEN");
        return token;
    }

    public static void main(String[] args)
    {
        String token = generateToken("zhangsan");
        System.out.println("token = " + token);
        String username = JwtUtil.getUsernameFromToken(token);
        System.out.println("username = " + username);
    }
}
