package com.blog.controller;

import com.blog.conf.Constant;
import com.blog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/14.
 */
@RestController
@RequestMapping(value = "/token")
public class TokenController {

    /**
     *
     * @param refresh cookie中的refreshToken
     * @return {
     *     "token": 用户token
     *     "refreshToken": 用于刷新的token
     * }
     */
    @RequestMapping(method = RequestMethod.GET)
    public Map refresh(@CookieValue("refresh") String refresh){
        Claims claims = JwtUtil.parseJWT(refresh);
        String subject  = claims.getSubject();
        String token = JwtUtil.createJWT(Constant.JWT_ID, claims.getIssuer(), subject, Constant.JWT_TTL);
        String refreshToken = JwtUtil.createJWT(Constant.JWT_ID, claims.getIssuer(), subject, Constant.JWT_REFRESH_TTL);
        Map m = new HashMap();
        m.put("token", token);
        m.put("refresh", refreshToken);
        return m;
    }
}
