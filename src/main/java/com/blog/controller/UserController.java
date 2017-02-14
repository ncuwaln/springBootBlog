package com.blog.controller;

import com.blog.error.UserDefinedException;
import com.blog.model.User;
import com.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login(String user){
//
//    }
//    @RequestMapping(method = RequestMethod.GET)
//    public String getUserByUsername(String username){
//        User u = userService.findUserByUsername(username);
//        System.out.print(u.getUsername());
//        return "successful";
//    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map register(String username, String password, String rePassword, String email) throws UserDefinedException {
        if (!rePassword.equals(password)){
            throw new UserDefinedException("两次输入密码不一致", 400);
        }
        User user = new User(username, password, email);
        userService.addUser(user);
        Map message = new HashMap();
        message.put("stats", "successful");
        return message;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(String email, String password) throws UserDefinedException, JsonProcessingException {
        return userService.login(email, password);
    }
}
