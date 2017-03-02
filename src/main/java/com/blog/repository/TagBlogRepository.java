package com.blog.repository;

import com.blog.model.TagBlog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
@Repository
public interface TagBlogRepository extends CrudRepository<TagBlog, Integer>{

    @Query("select tb from TagBlog tb where tb.tag_id=:tag_id")
    public List<TagBlog> findByTagId(@Param("tag_id") Integer tag_id);

    @Query("select tb from TagBlog  tb where tb.blog_id=:blog_id")
    public List<TagBlog> findByBlogId(@Param("blog_id") Integer blog_id);
}
