package com.epam.lab.news.bean;

import javax.persistence.*;

@Entity
@Table(name = "NEWS_TAGS")
public class NewsTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_tag_id")
    private Long id;

    @ManyToOne
    @Column(name = "id")
    private Long newsId;

    @ManyToOne
    @Column(name = "tag")
    private Long tagId;
}
