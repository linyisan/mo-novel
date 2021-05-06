package com.heng.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heng.vo.UserInfoVo;
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
    public static final String CLAIM_USERINFO = "USERINFO";
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
     * @param userInfoVo
     * @return
     */
    @SneakyThrows
    public static String generateToken(UserInfoVo userInfoVo)
    {
        HashMap<String, Object> clamins = new HashMap<>();
        clamins.put(CLAIM_USERINFO, userInfoVo);
        clamins.put("CREATE_TIME", EXPIRE_TIME);

        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .setClaims(clamins)
                .compact();
    }

    /**
     * 从请求头中获取TOKEN
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request)
    {
        String token = request.getHeader("X-TOKEN");
        return token;
    }

    /**
     * 从TOKEN从获取Clamins
     * @param token
     * @return 用户信息
     */
    @SneakyThrows
    public static UserInfoVo getUserInfoFormToken(String token)
    {
        Claims claims = getClaimsFromToken(token);
        return new ObjectMapper().convertValue(claims.get(CLAIM_USERINFO), UserInfoVo.class);
    }

    /**
     * 从token中获取JWT中的负载
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args)
    {
        UserInfoVo userInfoVo = new UserInfoVo(1L,
                "zhangsan",
                new String[]{"admin"},
                "https://sf3-ttcdn-tos.pstatp.com/img/user-avatar/0919a7c21d4dc335c27136900555d696~300x300.image"
        );
        String token = generateToken(userInfoVo);
        System.out.println("token = " + token);
        UserInfoVo userInfoVo1 = getUserInfoFormToken(token);
        System.out.println("userInfoVo1 = " + userInfoVo1);
    }
}
