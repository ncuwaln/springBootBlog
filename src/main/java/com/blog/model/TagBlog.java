package com.blog.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17.
 */
@Entity
@Table(name = "tag_blog")
@IdClass(TagBlog.class)
public class TagBlog implements Serializable{
    @Id
    @Column(name = "tag_id")
    private Integer tag_id;
    @Id
    @Column(name = "blog_id")
    private Integer blog_id;

    public TagBlog() {
    }

    public TagBlog(Integer tag_id, Integer blog_id) {
        this.tag_id = tag_id;
        this.blog_id = blog_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public Integer getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Integer blog_id) {
        this.blog_id = blog_id;
    }
}
