package com.blog.model;


import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/4.
 */
@Entity
@Table(name = "user")
public class User {
    private Integer id;
    private String username;
    private String hashPassword;
    private String email;

    public User(){}

    public User(String username, String hashPassword, String email) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username", unique = true, nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "hash_password", nullable = false)
    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
