package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "COMMENTS_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne()
    @JoinColumn(name = "NEWS_news_id")
    @JsonIgnore
    private News news;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /*public Long getNews() {
        return news;
    }

    public void setNews(Long news) {
        this.news = news;
    }*/

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
