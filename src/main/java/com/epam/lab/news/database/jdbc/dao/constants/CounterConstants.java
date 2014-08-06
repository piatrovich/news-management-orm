package com.epam.lab.news.database.jdbc.dao.constants;

/**
 * Defines SQL queries for working with counters
 *
 * @author Dzmitry Piatrovich
 */
public class CounterConstants {
    /** Keeps query searching articles counter */
    public static final String SQL_GET_ARTICLE_COUNTER =
            "SELECT _id, count FROM counters WHERE _id = 'aid'";

    /** Keeps query updating articles counter */
    public static final String SQL_UPDATE_ARTICLE_COUNTER =
            "UPDATE counters SET count = ? WHERE _id = 'aid'";

    /** Keeps query updating articles counter */
    public static final String MSG_GET_ARTICLE_COUNTER =
            "An error has occurred when trying to find article counter.";

    /** Keeps query updating articles counter */
    public static final String MSG_UPDATE_ARTICLE_COUNTER =
            "An error has occurred when trying to update article counter.";
}
