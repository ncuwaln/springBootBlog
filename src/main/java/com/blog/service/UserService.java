package com.blog.service;

import com.blog.conf.Constant;
import com.blog.error.UserDefinedException;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import com.blog.util.MD5Util;
import com.blog.util.MailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/2/4.
 */
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private Configuration configuration;

    public User getUser(int id){
        return userRepository.findOne(id);
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User addUser(User user) throws UserDefinedException, IOException, NoSuchAlgorithmException, TemplateException, MessagingException, InterruptedException {
        if (userRepository.findUserByEmail(user.getEmail()) != null){
            throw new UserDefinedException("邮箱已被注册", 400);
        }
        if (userRepository.findUserByUsername(user.getUsername()) != null){
            throw new UserDefinedException("用户名已存在", 400);
        }
        user.setHashPassword(MD5Util.String2MD5(user.getHashPassword()));
        user.setActive(0);
        user.setMd5(UUID.randomUUID().toString());
        Map<String, Object> map = new HashMap<String, Object>();
        String url = "";
        map.put("link", url);
        Template template = configuration.getTemplate("mail.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailUtil.buildMail(user.getEmail(), "MBLOG账号激活验证邮件", text);
        mailUtil.senMail(true);
        User u = userRepository.save(user);
        return u;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Map login(String email, String password) throws UserDefinedException, JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException {
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

    public void active(Integer id){
        userRepository.active(id);
    }
}
