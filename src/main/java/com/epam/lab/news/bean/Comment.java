package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class Comment extends MappedBean {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "COMMENTS_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
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

    /**
     * Getter for id
     *
     * @return Comment id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id Comment id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for comment text
     *
     * @return Comment text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for comment text
     *
     * @param text Comment text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for comment creation date
     *
     * @return Comment creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Setter for comment creation date
     *
     * @param creationDate Comment creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Setter for news
     *
     * @param news News
     */
    public void setNews(News news) {
        this.news = news;
    }

    /**
     * Getter for news
     *
     * @return News
     */
    public News getNews() {
        return news;
    }

}
