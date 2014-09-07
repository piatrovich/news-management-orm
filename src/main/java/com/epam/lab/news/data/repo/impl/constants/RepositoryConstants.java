package com.epam.lab.news.data.repo.impl.constants;

public class RepositoryConstants {

    public static final String MOST_COMMENTED =
            "SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, NEWS.CREATION_DATE, MODIFICATION_DATE " +
            "FROM NEWS JOIN (" +
                    "SELECT NEWS_NEWS_ID, COUNT(NEWS_NEWS_ID) AS COUNT " +
                    "FROM COMMENTS " +
                    "GROUP BY NEWS_NEWS_ID" +
            ") " +
            "ON NEWS.NEWS_ID = NEWS_NEWS_ID ORDER BY COUNT DESC";

    public static final String COUNT_BY_TAG =
            "SELECT COUNT(TAG) FROM NEWS_TAG WHERE TAG = :id";

    public static final String NEWS_BY_TAG =
            "SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, NEWS.CREATION_DATE, MODIFICATION_DATE " +
            "FROM NEWS JOIN NEWS_TAG " +
            "ON NEWS.NEWS_ID = NEWS_TAG.NEWS " +
            "WHERE NEWS_TAG.TAG = :id";

    public static final String COUNT_BY_AUTHOR =
            "SELECT COUNT(AUTHOR) FROM NEWS_AUTHOR WHERE AUTHOR = :id";

    public static final String NEWS_BY_AUTHOR =
            "SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, NEWS.CREATION_DATE, MODIFICATION_DATE " +
            "FROM NEWS JOIN NEWS_AUTHOR " +
            "ON NEWS.NEWS_ID = NEWS_AUTHOR.NEWS " +
            "WHERE NEWS_AUTHOR.AUTHOR = :id";

    public static final String TAGS_BY_NEWS_ID =
            "SELECT TAG.TAG_ID, TAG.TAG_NAME FROM TAG " +
            "JOIN NEWS_TAG ON TAG.TAG_ID = NEWS_TAG.TAG " +
            "WHERE NEWS_TAG.NEWS = :id";

    public static final String AUTHORS_BY_NEWS_ID =
            "SELECT AUTHOR.AUTHOR_ID, AUTHOR.AUTHOR_NAME FROM AUTHOR " +
            "JOIN NEWS_AUTHOR ON AUTHOR.AUTHOR_ID = NEWS_AUTHOR.AUTHOR " +
            "WHERE NEWS_AUTHOR.NEWS = :id";

}
