package com.epam.lab.news.data.repo.impl.constants;

public class RepositoryConstants {

    public static final String MOST_COMMENTED =
            "SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, NEWS.CREATION_DATE, MODIFICATION_DATE "
            + "FROM NEWS INNER JOIN (SELECT NEWS_NEWS_ID, COUNT(NEWS_NEWS_ID) AS COUNT FROM COMMENTS GROUP BY NEWS_NEWS_ID) "
            + "ON NEWS.NEWS_ID = NEWS_NEWS_ID ORDER BY COUNT DESC";

    public static final String COUNT_BY_TAG =
            "SELECT COUNT(TAG) FROM NEWS_TAG WHERE TAG = :id";

    public static final String COUNT_BY_AUTHOR =
            "SELECT COUNT(AUTHOR) FROM NEWS_AUTHOR WHERE AUTHOR = :id";

}
