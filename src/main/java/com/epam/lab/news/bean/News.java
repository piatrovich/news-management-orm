package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NEWS")
public class News {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "NEWS_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
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

    @OneToMany(mappedBy = "news")
    @JsonIgnore
    private List<Comment> comments;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "NEWS_TAG",
            joinColumns = {@JoinColumn(name = "NEWS_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private Set<Tag> tags = new HashSet<Tag>();

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

}
