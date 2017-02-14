package com.blog.repository;

import com.blog.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */
@Repository
@Table(name = "user")
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.username=:username")
    public User findUserByUsername(@Param("username") String username);

    @Query("select u from User u where u.email=:email")
    public User findUserByEmail(@Param("email")String email);

    @Query("select u from User u where u.username like :username escape '/'")
    public List<User> findUserByKeywords(@Param("username") String username);
}
