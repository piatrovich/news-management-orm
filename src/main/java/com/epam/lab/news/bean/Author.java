package com.epam.lab.news.bean;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany
    @JoinColumn()
    private Long id;

    @Column(name = "name")
    private String name;

}
