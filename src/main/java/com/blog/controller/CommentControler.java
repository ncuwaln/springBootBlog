package com.blog.controller;

import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/21.
 */

@RestController
@RequestMapping(value = "/comment")
public class CommentControler {
    @Autowired
    private CommentService commentService;
}
