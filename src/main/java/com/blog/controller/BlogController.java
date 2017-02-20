package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.Tag;
import com.blog.model.TagBlog;
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
     * @param title 文章标题 str
     * @param body 文章内容 str
     * @param token token cookie中的token
     * @param tagMessage 标签 [str]
     * @return {
     *     "status": "successful"
     * }
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map addBlog(String title, String body, List<String> tagMessage, @CookieValue("token") String token) throws IOException {
        String subject = JwtUtil.parseJWT(token).getSubject();
        Map m = (Map) JsonUtil.json2object(subject, Map.class);
        Integer user_id = (Integer)m.get("user_id");
        Blog tempBlog = blogService.writeBlog(user_id, title, body);
        Tag tempTag = null;
        for (String t:tagMessage){
            if (tagService.findTagByMessage(t) == null){
                tempTag = tagService.addTag(new Tag(t));
            }else {
                tempTag = tagService.findTagByMessage(t);
            }
            tagService.label(new TagBlog(tempTag.getId(), tempBlog.getId()));
        }
        Map result = new HashMap();
        result.put("status", "successful");
        return result;
    }

    /**
     *
     * @param keywords 搜索的关键字
     * @param type 类型: title, author里面选
     * @return {
     * [{
     *     "blog_id": 博客ID(一般不要用),
     *     "body": 博客主体,
     *     "title": 博客题目,
     *     "author": 作者,
     *     "create_date": 日期(datetime类型)
     * }]
     * }
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

    /**
     *
     * @param pages 页数 str
     * @param limits 一页显示的范围 int
     * @return [Blog]
     */
    @RequestMapping("/list")
    public List<Blog> ListBlog(Integer pages, Integer limits){
        return blogService.listBlog(pages, limits);
    }
}
