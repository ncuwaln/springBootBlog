package com.blog.repository;

import com.blog.model.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/12.
 */
@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer>{

    @Query("select b from Blog b where b.title like :title escape '/'")
    public  List<Blog> findBlogByTitle(@Param("title") String title);

    @Query("select b from Blog b where b.author=:author")
    public List<Blog> findBlogByAuthor(@Param("author") String author);

    @Query("select b from Blog b where b.author like :keywords escape '/'")
    public List<Blog> findBlogByAuthorKeywords(@Param("keywords") String keywords);

    @Query(value = "SELECT * from blog ORDER BY UNIX_TIMESTAMP(blog.create_date) DESC LIMIT :start, :end", nativeQuery = true)
    public List<Blog> listBlog(@Param("start") Integer start, @Param("end") Integer end);
}
