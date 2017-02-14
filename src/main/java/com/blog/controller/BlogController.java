package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.BlogService;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/12.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addBlog(String title, String body, @CookieValue("token") String token) throws IOException {
        Date create_date = new Date(System.currentTimeMillis());
        String subject = JwtUtil.parseJWT(token).getSubject();
        Map m = (Map)JsonUtil.json2object(subject, Map.class);
        Integer user_id = (Integer)m.get("user_id");
        blogService.writeBlog(user_id, title, body);
        Map result = new HashMap();
        result.put("status", "successful");
        return result;
    }

    /**
     *
     * @param keywords 搜索的关键字
     * @param type 类型: title, author里面选
     * @return
     */
    @RequestMapping(value = "/search")
    public List<Blog> searchBlog(String keywords, String type){
        if (type.equals("title")){
            return blogService.findBlogsByTitle(keywords);
        }
        else{
            return blogService.findBlogsByAuthorName(keywords);
        }
    }
}
