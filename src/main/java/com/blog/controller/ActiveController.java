package com.blog.controller;

import com.blog.model.User;
import com.blog.service.UserService;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/23.
 */

@RestController
@RequestMapping(value = "/active")
public class ActiveController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{md5}", method = RequestMethod.GET)
    public Map active(@PathParam("md5") String md5, @CookieValue("token") String token) throws IOException {
        Claims claims = JwtUtil.parseJWT(token);
        String subject = claims.getSubject();
        Map m = (Map) JsonUtil.json2object(subject, Map.class);
        Integer id = (Integer) m.get("user_id");
        User user = userService.getUser(id);
        if (user.getActive() <= 0 && user.getMd5().equals(md5)){
            userService.active(id);
        }
        return (Map) new HashMap().put("status", "successful");
    }
}
