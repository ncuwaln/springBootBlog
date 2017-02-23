package com.blog.controller;

import com.blog.conf.Constant;
import com.blog.error.UserDefinedException;
import com.blog.model.User;
import com.blog.service.UserService;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/4.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    /**
     *
     * @param username 用户名 str
     * @param password 密码 str
     * @param rePassword 第二次输入的密码 str
     * @param email 邮箱 str
     * @return {
     *     "status": "successful"
     *     "token": 用户token
     *     "refreshToken": 用于刷新的token
     * }
     * @throws UserDefinedException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map register(String username, String password, String rePassword, String email) throws UserDefinedException, IOException, NoSuchAlgorithmException, MessagingException, TemplateException, InterruptedException {
        if (!rePassword.equals(password)){
            throw new UserDefinedException("两次输入密码不一致", 400);
        }
        User user = new User(username, password, email);
        user = userService.addUser(user);
        Map mapSubject = new HashMap();
        mapSubject.put("user_id", user.getId());
        mapSubject.put("email", user.getEmail());
        String subject = JsonUtil.object2json(mapSubject);
        String token = JwtUtil.createJWT(Constant.JWT_ID, user.getUsername(), subject, Constant.JWT_TTL);
        String refreshToken = JwtUtil.createJWT(Constant.JWT_ID, user.getUsername(), subject, Constant.JWT_REFRESH_TTL);
        Map m = new HashMap();
        m.put("token", token);
        m.put("refresh", refreshToken);
        m.put("status", "successful");
        return m;
    }

    /**
     *
     * @param email 邮箱
     * @param password 密码
     * @return {
     *     "token": 用户token
     *     "refreshToken": 用于刷新的token
     * }
     * @throws UserDefinedException
     * @throws JsonProcessingException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(String email, String password) throws UserDefinedException, JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.login(email, password);
    }
}
