package com.epam.lab.news.suite;

import com.epam.lab.news.repo.AuthorRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all application tests
 *
 * @author Dzmitry Piatrovich
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorRepositoryTest.class
})
public class TestSuite {
}
