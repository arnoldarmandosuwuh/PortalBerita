package com.charisma.portalberita.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bayu Charisma Putra on 4/5/2019.
 */
public class News {

    private int id;
    private int authorId;
    private String author;
    private String title;
    private String content;
    private String picture;
    private Date dateCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCreatedStr() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(dateCreated);
    }
}
