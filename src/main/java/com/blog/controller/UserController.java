package com.blog.controller;

import com.blog.error.UserDefinedException;
import com.blog.model.User;
import com.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * }
     * @throws UserDefinedException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map register(String username, String password, String rePassword, String email) throws UserDefinedException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!rePassword.equals(password)){
            throw new UserDefinedException("两次输入密码不一致", 400);
        }
        User user = new User(username, password, email);
        userService.addUser(user);
        Map message = new HashMap();
        message.put("status", "successful");
        return message;
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
