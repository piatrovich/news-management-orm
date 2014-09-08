package com.epam.lab.news.data.repo.impl.constants;

/**
 * Constants keeps SQL queries
 */
public class RepositoryConstants {

    /**
     * Returns list of most commented news sorting by DESC.<br />
     * Needs to set size as parameter
     */
    public static final String MOST_COMMENTED =
            "SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, NEWS.CREATION_DATE, MODIFICATION_DATE " +
            "FROM NEWS JOIN (" +
                    "SELECT NEWS_NEWS_ID, COUNT(NEWS_NEWS_ID) AS COUNT " +
                    "FROM COMMENTS " +
                    "GROUP BY NEWS_NEWS_ID" +
            ") " +
            "ON NEWS.NEWS_ID = NEWS_NEWS_ID ORDER BY COUNT DESC";

    /**
     * Returns number of count contains some tag. <br />
     * Needs to set tag id
     */
    public static final String COUNT_BY_TAG =
            "SELECT COUNT(TAG) FROM NEWS_TAG WHERE TAG = :id";

    /**
     * Returns list of news contains some tag. <br />
     * Needs to set tag id
     */
    public static final String NEWS_BY_TAG =
            "SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, NEWS.CREATION_DATE, MODIFICATION_DATE " +
            "FROM NEWS JOIN NEWS_TAG " +
            "ON NEWS.NEWS_ID = NEWS_TAG.NEWS " +
            "WHERE NEWS_TAG.TAG = :id";

    /**
     * Returns number of count contains some author. <br />
     * Needs to set author id
     */
    public static final String COUNT_BY_AUTHOR =
            "SELECT COUNT(AUTHOR) FROM NEWS_AUTHOR WHERE AUTHOR = :id";

    /**
     * Returns list of news contains some author. <br />
     * Needs to set author id
     */
    public static final String NEWS_BY_AUTHOR =
            "SELECT NEWS.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, NEWS.CREATION_DATE, MODIFICATION_DATE " +
            "FROM NEWS JOIN NEWS_AUTHOR " +
            "ON NEWS.NEWS_ID = NEWS_AUTHOR.NEWS " +
            "WHERE NEWS_AUTHOR.AUTHOR = :id";

    /**
     * Returns list of tags by news id<br />
     * Needs to set news id as parameter
     */
    public static final String TAGS_BY_NEWS_ID =
            "SELECT TAG.TAG_ID, TAG.TAG_NAME FROM TAG " +
            "JOIN NEWS_TAG ON TAG.TAG_ID = NEWS_TAG.TAG " +
            "WHERE NEWS_TAG.NEWS = :id";

    /**
     * Returns list of authors by news id<br />
     * Needs to set news id as parameter
     */
    public static final String AUTHORS_BY_NEWS_ID =
            "SELECT AUTHOR.AUTHOR_ID, AUTHOR.AUTHOR_NAME FROM AUTHOR " +
            "JOIN NEWS_AUTHOR ON AUTHOR.AUTHOR_ID = NEWS_AUTHOR.AUTHOR " +
            "WHERE NEWS_AUTHOR.NEWS = :id";

}
