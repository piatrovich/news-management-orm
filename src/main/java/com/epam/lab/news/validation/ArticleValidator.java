package com.epam.lab.news.validation;

import com.epam.lab.news.bean.News;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Minimal validator implementation
 *
 * @author Dzmitry Piatrovich
 */
@Component
public class ArticleValidator implements Validator {

    /**
     * This method checks can this validator validate
     *
     * @param aClass Class name
     * @return True if support
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return News.class.isAssignableFrom(aClass);
    }

    /**
     * Checks only "must have" field values
     *
     * @param object Target object
     * @param errors Resulting errors
     */
    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                ValidationConstants.FIELD_TITLE,
                ValidationConstants.CODE_TITLE_EMPTY,
                ValidationConstants.MSG_TITLE_EMPTY);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                ValidationConstants.FIELD_DESCRIPTION,
                ValidationConstants.CODE_DESCRIPTION_EMPTY,
                ValidationConstants.MSG_DESCRIPTION_EMPTY);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                ValidationConstants.FIELD_TEXT,
                ValidationConstants.CODE_TEXT_EMPTY,
                ValidationConstants.MSG_TEXT_EMPTY);
    }

    /**
     * Return object which contains status and errors if <code>status == false</code>
     *
     * @param news Article which need to be validated
     * @return ValidationResult object
     */
    public ValidationResult validate(News news){
        DataBinder binder = new DataBinder(news);
        binder.setValidator(this);
        binder.validate();
        if(binder.getBindingResult().hasErrors()) {
            ValidationResult result = new ValidationResult(false);
            Map<String, String> values = new HashMap<String, String>();
            for(FieldError field : binder.getBindingResult().getFieldErrors()) {
                values.put(field.getCode(),field.getDefaultMessage());
            }
            result.setResult(values);
            return result;
        }
        return new ValidationResult(true);
    }

}
