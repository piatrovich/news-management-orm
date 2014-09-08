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

/**
 * Describes news
 */
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

    /**
     * Getter for news id
     *
     * @return News id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for news id
     *
     * @param id  News id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for news title
     *
     * @return News title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for news title
     *
     * @param title News title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for short text
     *
     * @return News short text
     */
    public String getShortText() {
        return shortText;
    }

    /**
     * Setter for short text
     *
     * @param shortText News short text
     */
    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    /**
     * Getter for full text
     *
     * @return news full text
     */
    public String getFullText() {
        return fullText;
    }

    /**
     * Setter for full text
     *
     * @param fullText news full text
     */
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    /**
     * Getter for creation date
     *
     * @return News creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Setter for creation date
     *
     * @param creationDate news creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Getter for modification date
     *
     * @return News modification date
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * Setter for modification date
     *
     * @param modificationDate Modification date
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * Getter for comments
     *
     * @return  News comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Setter for comments
     *
     * @param comments News comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Getter for tags
     *
     * @return news tags
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * Setter for tags
     *
     * @param tags news tags
     */
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

    /**
     * Getter for authors
     *
     * @return News authors
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * Setter for authors
     *
     * @param authors News authors
     */
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
