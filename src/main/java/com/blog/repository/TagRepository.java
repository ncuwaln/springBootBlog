package com.blog.repository;

import com.blog.model.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Administrator on 2017/2/17.
 */
public interface TagRepository extends CrudRepository<Tag, Integer>{

    @Query("select t from Tag t where t.message=:message")
    public Tag findTagByMessage(@Param("message") String message);
}
