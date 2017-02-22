package com.blog.controller;

import com.blog.error.UserDefinedException;
import com.blog.model.Comment;
import com.blog.model.User;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21.
 */

@RestController
@RequestMapping(value = "/comment")
public class CommentControler {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addComment(String body, Integer blog_id,
                          Integer comment_id, @CookieValue("token") String token) throws IOException, UserDefinedException {
        Claims claims = JwtUtil.parseJWT(token);
        String subject = claims.getSubject();
        Map m = (Map) JsonUtil.json2object(subject, Map.class);
        Integer user_id = (Integer) m.get("user_id");
        commentService.addComment(body, blog_id, user_id, comment_id);
        return (Map) new HashMap().put("status", "successful");
    }

    @RequestMapping(value = "/delete")
    public Map deleteComment(Integer comment_id, @CookieValue("token") String token) throws IOException, UserDefinedException {
        Claims claims = JwtUtil.parseJWT(token);
        String subject = claims.getSubject();
        Map m = (Map) JsonUtil.json2object(subject, Map.class);
        Integer user_id = (Integer) m.get("user_id");
        User user = userService.getUser(user_id);
        commentService.deleteComment(comment_id, user.getUsername());
        return (Map) new HashMap().put("status", "successful");
    }
}
