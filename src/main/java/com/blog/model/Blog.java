package com.blog.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/12.
 */
@Entity
@Table(name = "blog")
public class Blog {
    private Integer id;
    private String body;
    private String title;
    private Integer user_id;
    private Date create_date;

    public Blog() {
    }

    public Blog(String body, String title, Integer user_id, Date create_date) {
        this.body = body;
        this.title = title;
        this.user_id = user_id;
        this.create_date = create_date;
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

    @Column(name = "body", nullable = false)
    @Type(type = "text")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "user_id", nullable = false)
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Column(name = "create_date", nullable = false)
    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
