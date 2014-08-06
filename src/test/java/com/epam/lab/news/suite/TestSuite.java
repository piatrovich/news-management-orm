package com.epam.lab.news.suite;

import com.epam.lab.news.api.APITest;
import com.epam.lab.news.controller.ViewsControllerTest;
import com.epam.lab.news.exception.GlobalExceptionHandlerTest;
import com.epam.lab.news.pool.PoolTest;
import com.epam.lab.news.validation.ArticleValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Starts all tests
 *
 * @author Dzmitry Piatrovich
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        APITest.class,
        ViewsControllerTest.class,
        ArticleValidatorTest.class,
        GlobalExceptionHandlerTest.class,
        PoolTest.class
})
public class TestSuite {
}
