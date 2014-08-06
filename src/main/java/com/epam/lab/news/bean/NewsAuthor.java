package com.epam.lab.news.bean;

import javax.persistence.*;

@Entity
@Table(name = "NEWS_AUTHOR")
public class NewsAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_author_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Long newsId;

    @ManyToOne
    @JoinColumn(name = "author")
    private Long authorId;
}
