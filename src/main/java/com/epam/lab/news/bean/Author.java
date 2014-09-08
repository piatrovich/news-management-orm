package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Describes author
 */
@Entity
@Table(name = "AUTHOR")
public class Author extends MappedBean {
    /**
     * Keeps author id
     */
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "AUTHOR_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "AUTHOR_ID", updatable = true)
    private Long id;

    /**
     * Keeps author name
     */
    @Column(name = "AUTHOR_NAME", nullable = false)
    private String name;

    /**
     * Author's newses
     */
    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private Set<News> newses;

    /**
     * Getter for id
     *
     * @return Author id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id Author id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for author name
     *
     * @return Author name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name Author name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of newses
     *
     * @return Set of newses
     */
    public Set<News> getNewses() {
        return newses;
    }

    /**
     * Setter for newses
     *
     * @param newses Set of newses
     */
    public void setNewses(Set<News> newses) {
        this.newses = newses;
    }
}
