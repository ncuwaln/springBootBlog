package com.blog.service;

import com.blog.error.UserDefinedException;
import com.blog.model.Comment;
import com.blog.repository.BlogRepository;
import com.blog.repository.CommentRepository;
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

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Comment addComment(Comment comment) throws UserDefinedException {
        Comment savedComment = null;
        if (comment.getComment_id() <= 0){
            if (blogRepository.findOne(comment.getBlog_id()) == null){
                throw new UserDefinedException("博客不存在", 404);
            }
        }
        else if (commentRepository.findOne(comment.getComment_id()) == null
                || blogRepository.findOne(comment.getBlog_id()) == null){
            throw new UserDefinedException("评论引用的评论或博客不存在", 404);
        }
        return commentRepository.save(comment);
    }

    public List<Comment> findCommentsByBlog(Integer blog_id){
        return commentRepository.findCommentsByBlogId(blog_id);
    }

    public List<Comment> findCommentsByReviewer(String reviewer){
        return commentRepository.findCommentsByReviewer(reviewer);
    }

    public void deleteComment(Integer id){
        commentRepository.delete(id);
    }
}
