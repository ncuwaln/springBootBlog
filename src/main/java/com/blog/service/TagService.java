package com.blog.service;

import com.blog.model.Tag;
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

    public Tag findTagByMessage(String message){
        return tagRepository.findTagByMessage(message);
    }

    public void addTag(Tag tag){
        tagRepository.save(tag);
    }
}
