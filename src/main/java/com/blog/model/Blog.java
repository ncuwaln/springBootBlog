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
    private String author;
    private Date create_date;

    public Blog() {
    }

    public Blog(String body, String title, String  author, Date create_date) {
        this.body = body;
        this.title = title;
        this.author = author;
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

    @Column(name = "title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false, length = 30)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "create_date", nullable = false)
    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
