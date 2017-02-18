package com.blog.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/17.
 */
@Entity
@Table(name = "tag")
public class Tag {
    private Integer id;
    private String message;

    public Tag() {
    }

    public Tag(String message) {
        this.message = message;
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

    @Column(name = "message", nullable = false, unique = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
