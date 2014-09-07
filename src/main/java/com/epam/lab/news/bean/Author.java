package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "AUTHOR")
public class Author extends MappedBean {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "AUTHOR_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "AUTHOR_ID", updatable = true)
    private Long id;

    @Column(name = "AUTHOR_NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private Set<News> newses;

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

    public Set<News> getNewses() {
        return newses;
    }

    public void setNewses(Set<News> newses) {
        this.newses = newses;
    }
}
