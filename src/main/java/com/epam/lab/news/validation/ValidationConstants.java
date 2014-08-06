package com.epam.lab.news.validation;

/**
 * Constants for article validator
 *
 * @author Dzmitry Piatrovich
 */
class ValidationConstants {
    /** Keeps field name of article title */
    public static final String FIELD_TITLE = "title";

    /** Keeps field name of article description */
    public static final String FIELD_DESCRIPTION = "description";

    /** Keeps field name of article text */
    public static final String FIELD_TEXT = "text";

    /** Keeps error code for empty title */
    public static final String CODE_TITLE_EMPTY = "title.empty";

    /** Keeps error code for empty description */
    public static final String CODE_DESCRIPTION_EMPTY = "description.empty";

    /** Keeps error code for empty text */
    public static final String CODE_TEXT_EMPTY = "text.empty";

    /** Keeps error message for empty title  */
    public static final String MSG_TITLE_EMPTY = "Title can not be empty";

    /** Keeps error message for empty description */
    public static final String MSG_DESCRIPTION_EMPTY = "Description can not be empty";

    /** Keeps error message for empty text */
    public static final String MSG_TEXT_EMPTY = "Text can not be empty";
}
