package com.zy.blog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "token.jwt")
public class JwtUtils {
    //密钥
    private String secret;
    //过期时间
    private long expire;
    private String header;
    private static final ConcurrentHashMap<String,Object> sessionMap = new ConcurrentHashMap<>();
    //生成 jwt token
    public String generateToken(long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime()+expire*1000);
        return Jwts.builder().setHeaderParam("type","JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 获取jwt的信息
     * @param token
     * @return
     */
    public Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            log.debug("Validate is token error" +e);
            return null;
        }
    }

    /**
     * token是否过期
     * @param expiration
     * @return
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
