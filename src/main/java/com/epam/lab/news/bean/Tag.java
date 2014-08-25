package com.epam.lab.news.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAG")
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "TAG_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "TAG_ID")
    private Long id;

    @Column(name = "TAG_NAME", nullable = false)
    private String name;

    @ManyToMany
    @JoinColumn(name = "news_id")
    @JsonIgnore
    private Set<News> newses = new HashSet<News>();

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

    @Override
    public int hashCode() {
        return (int)(id + ((name == null) ? 0: name.hashCode()));
    }

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

    public Set<News> getNewses() {
        return newses;
    }

    public void setNewses(Set<News> newses) {
        this.newses = newses;
    }
}
