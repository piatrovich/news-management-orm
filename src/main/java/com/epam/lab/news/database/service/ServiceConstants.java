package com.epam.lab.news.database.service;

/**
 * Defines constants for service implementation
 *
 * @author Dzmitry Piatrovich
 */
public class ServiceConstants {
    /** Error message for 'find all articles' action */
    public static final String MSG_FIND_ALL_ACTION =
            "Service error: Can not find all articles.";

    /** Error message for 'find single article' action */
    public static final String MSG_FIND_SINGLE_ACTION =
            "Service error: Can not find single article.";

    /** Error message for 'create article' action */
    public static final String MSG_CREATE_ACTION =
            "Service error: Can not create article.";

    /** Error message for 'update article' action */
    public static final String MSG_UPDATE_ACTION =
            "Service error: Can not update article.";

    /** Error message for 'delete article' action */
    public static final String MSG_DELETE_ACTION =
            "Service error: Can not delete article.";

    /** Keeps id of counter which contains last article id */
    public static final String COUNTER_NAME_OF_ARTICLE = "aid";
}
