package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "NEWS")
public class News extends MappedBean {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "NEWS_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "news_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "short_text")
    private String shortText;

    @Column(name = "full_text")
    private String fullText;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "modification_date")
    private Date modificationDate;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "NEWS_TAG",
            joinColumns = {@JoinColumn(name = "NEWS")},
            inverseJoinColumns = {@JoinColumn(name = "TAG")})
    @JsonIgnore
    private Set<Tag> tags;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "NEWS_AUTHOR",
            joinColumns = {@JoinColumn(name = "NEWS")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR")})
    @JsonIgnore
    private Set<Author> authors;

    @OneToMany(orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "NEWS_NEWS_ID", updatable = false)
    @JsonIgnore
    private List<Comment> comments;

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

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("News [ id : ")
                .append(id)
                .append(", short_text : ")
                .append(shortText)
                .append(", full_text : ")
                .append(fullText)
                .append(", creation_date : ")
                .append(creationDate)
                .append(", modification_date : ")
                .append(modificationDate)
                .append(" ]")
                .toString();
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
