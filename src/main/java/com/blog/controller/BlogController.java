package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.Tag;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.util.JsonUtil;
import com.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/12.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    /**
     *
     * @param title 文章标题
     * @param body 文章内容
     * @param token token
     * @param tag 标签
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addBlog(String title, String body, List<Tag> tag, @CookieValue("token") String token) throws IOException {
        for (Tag t:tag){
            if (tagService.findTagByMessage(t.getMessage()) == null){
                tagService.addTag(t);
            }
        }
        Date create_date = new Date(System.currentTimeMillis());
        String subject = JwtUtil.parseJWT(token).getSubject();
        Map m = (Map) JsonUtil.json2object(subject, Map.class);
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
