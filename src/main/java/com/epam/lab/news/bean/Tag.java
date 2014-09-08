package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describes tag
 */
@Entity
@Table(name = "TAG")
public class Tag extends MappedBean {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "TAG_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "TAG_ID", updatable = true)
    private Long id;

    @Column(name = "TAG_NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<News> newses;

    /**
     * Getter of tag id
     *
     * @return Tag id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for tag id
     *
     * @param id Tag id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for tag name
     *
     * @return Tag name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for tag name
     *
     * @param name Tag name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Equals implementation
     *
     * @param obj Object
     * @return True if equals
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Tag tag = (Tag) obj;
        if (!id.equals(tag.id))
            return false;
        if (name == null){
            if (tag.name != null)
                return false;
        } else if (!name.equals(tag.name))
            return false;
        return true;
    }

    /**
     * Hashcode implementation
     *
     * @return Int value
     */
    @Override
    public int hashCode() {
        return (int)((id == null ? 0 : id) + (name == null ? 0: name.hashCode()));
    }

    /**
     * ToString implementation
     * @return
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append("Tag [ id : ")
                .append(id)
                .append(", name : ")
                .append(name)
                .append(" ]")
                .toString();
    }

    /**
     * Getter for news
     *
     * @return News set
     */
    public Set<News> getNewses() {
        return newses;
    }

    /**
     * Setter for news
     *
     * @param newses News set
     */
    public void setNewses(Set<News> newses) {
        this.newses = newses;
    }
}
