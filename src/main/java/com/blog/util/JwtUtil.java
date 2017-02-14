package com.blog.util;

import com.blog.conf.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/4.
 */
@Component
public class JwtUtil {
    public static String createJWT(String id, String issuer, String subject, long ttlMillis){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] secret = org.apache.tomcat.util.codec.binary.Base64
                        .decodeBase64(Constant.JWT_SECRET);
        SecretKey secretKey = new SecretKeySpec(secret, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                                .setId(id)
                                .setSubject(subject)
                                .setIssuer(issuer)
                                .setIssuedAt(now)
                                .signWith(signatureAlgorithm, secretKey);

        if (ttlMillis > 0){
            long expMillis = nowMillis+ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }


    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser().setSigningKey(org.apache.tomcat.util.codec.binary.Base64.decodeBase64(Constant.JWT_SECRET))
                                    .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
