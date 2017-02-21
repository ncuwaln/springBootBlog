package com.blog.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/21.
 */
@Entity
@Table(name = "comment")
public class Comment {
    private Integer id;
    private String body;
    private Integer blog_id;
    private String reviewer;
    // 如果该评论是用于回复评论,则comment_id是回复的评论的id,如果不是回复评论的评论,该值为0
    private Integer comment_id;

    public Comment() {
    }

    public Comment(String body, Integer blog_id, String reviewer, Integer comment_id) {
        this.body = body;
        this.blog_id = blog_id;
        this.reviewer = reviewer;
        this.comment_id = comment_id;
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

    @Column(name = "blog_id", nullable = false)
    public Integer getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Integer blog_id) {
        this.blog_id = blog_id;
    }

    @Column(name = "reviewer", nullable = false, length = 30)
    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Column(name = "comment_id")
    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }
}
