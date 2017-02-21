package com.blog.repository;

import com.blog.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

@Component
public interface CommentRepository extends CrudRepository<Comment, Integer>{

    @Query("select c from Comment c where c.blog_id=:blog_id")
    public List<Comment> findCommentsByBlogId(@Param("blog_id") Integer blog_id);

    @Query("select c from Comment c where c.reviewer=:reviewer")
    public List<Comment> findCommentsByReviewer(@Param("reviewer") String reviewer);
}
