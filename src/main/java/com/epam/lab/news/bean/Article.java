package com.epam.lab.news.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Describes article bean
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@Document(collection = "articles")
public class Article {
    private @Id Long id;
    private String title;
    private String description;
    private String text;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Article => [ _id = ")
                .append(id)
                .append("title = ")
                .append(title)
                .append("description = ")
                .append(description)
                .append("text = ")
                .append(text)
                .toString();
    }

}
