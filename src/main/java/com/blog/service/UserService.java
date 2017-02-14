package com.blog.service;

import com.blog.conf.Constant;
import com.blog.error.UserDefinedException;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import com.blog.util.MD5Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/4.
 */
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUser(int id){
        return userRepository.findOne(id);
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void addUser(User user) throws UserDefinedException {
        if (userRepository.findUserByEmail(user.getEmail()) != null){
            throw new UserDefinedException("邮箱已被注册", 400);
        }
        if (userRepository.findUserByUsername(user.getUsername()) != null){
            throw new UserDefinedException("用户名已存在", 400);
        }
        user.setHashPassword(MD5Util.String2MD5(user.getHashPassword()));
        userRepository.save(user);
    }

    public Map login(String email, String password) throws UserDefinedException, JsonProcessingException {
        User user = userRepository.findUserByEmail(email);
        if (!user.getHashPassword().equals(MD5Util.String2MD5(password))){
            throw new UserDefinedException("账号或密码错误", 403);
        }
        Map mapSubject = new HashMap();
        mapSubject.put("user_id", user.getId());
        mapSubject.put("email", user.getEmail());
        String subject = JsonUtil.object2json(mapSubject);
        String token = JwtUtil.createJWT(Constant.JWT_ID, user.getUsername(), subject, Constant.JWT_TTL);
        String refreshToken = JwtUtil.createJWT(Constant.JWT_ID, user.getUsername(), subject, Constant.JWT_REFRESH_TTL);
        Map m = new HashMap();
        m.put("token", token);
        m.put("refresh", refreshToken);
        return m;
    }

    private boolean is_email(String account_id){
        return userRepository.findUserByEmail(account_id) != null;
    }
}