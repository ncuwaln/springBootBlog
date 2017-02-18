package com.blog.repository;

import com.blog.model.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */
public interface BlogRepository extends CrudRepository<Blog, Integer>{

    @Query("select b from Blog b where b.title like :title escape '/'")
    public  List<Blog> findBlogByTitle(@Param("title") String title);

    @Query("select b from Blog b where b.author=:author")
    public List<Blog> findBlogByAuthor(@Param("author") String author);

    @Query("select b from Blog b where b.author like :keywords escape '/'")
    public List<Blog> findBlogByAuthorKeywords(@Param("keywords") String keywords);
}
