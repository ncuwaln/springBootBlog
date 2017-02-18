package com.blog.service;

import com.blog.model.Tag;
import com.blog.model.TagBlog;
import com.blog.repository.TagBlogRepository;
import com.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/2/17.
 */
@Component
public class TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagBlogRepository tagBlogRepository;

    public Tag findTagByMessage(String message){
        return tagRepository.findTagByMessage(message);
    }

    public Tag addTag(Tag tag){
        return tagRepository.save(tag);
    }

    public TagBlog label(TagBlog tagBlog){
        return tagBlogRepository.save(tagBlog);
    }
}
