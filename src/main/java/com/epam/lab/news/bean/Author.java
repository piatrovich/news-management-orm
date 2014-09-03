package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "AUTHOR_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "AUTHOR_ID", updatable = true)
    private Long id;

    @Column(name = "AUTHOR_NAME", nullable = false)
    private String name;

    @ManyToMany
    @JoinColumn(name = "news_id")
    @JsonIgnore
    private List<News> newses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<News> getNewses() {
        return newses;
    }

    public void setNewses(List<News> newses) {
        this.newses = newses;
    }
}
