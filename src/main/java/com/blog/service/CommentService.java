package com.blog.service;

import com.blog.error.UserDefinedException;
import com.blog.model.Comment;
import com.blog.model.User;
import com.blog.repository.BlogRepository;
import com.blog.repository.CommentRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

@Component
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Comment addComment(String body, Integer blog_id,
                              Integer user_id, Integer comment_id) throws UserDefinedException {
        Comment savedComment = null;
        if (comment_id <= 0){
            if (blogRepository.findOne(blog_id) == null){
                throw new UserDefinedException("博客不存在", 404);
            }
        }
        else if (commentRepository.findOne(comment_id) == null
                || blogRepository.findOne(blog_id) == null){
            throw new UserDefinedException("评论引用的评论或博客不存在", 404);
        }
        User user = userRepository.findOne(user_id);
        return commentRepository.save(new Comment(body, blog_id, user.getUsername(), comment_id));
    }

    public List<Comment> findCommentsByBlog(Integer blog_id){
        return commentRepository.findCommentsByBlogId(blog_id);
    }

    public List<Comment> findCommentsByReviewer(String reviewer){
        return commentRepository.findCommentsByReviewer(reviewer);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteComment(Integer id, String reviewer) throws UserDefinedException {
        Comment comment = commentRepository.findOne(id);
        if (comment == null && !comment.getReviewer().equals(reviewer)){
            throw new UserDefinedException("评论无法删除", 400);
        }
        commentRepository.delete(id);
    }
}
