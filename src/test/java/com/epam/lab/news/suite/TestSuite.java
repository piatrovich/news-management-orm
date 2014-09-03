package com.epam.lab.news.suite;

import com.epam.lab.news.repo.AuthorRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorRepositoryTest.class
})
public class TestSuite {
}
