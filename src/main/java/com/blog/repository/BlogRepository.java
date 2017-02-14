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

    @Query("select b from Blog b where b.user_id=:user_id")
    public List<Blog> findBlogByAuthor(@Param("user_id") Integer user_id);
}
