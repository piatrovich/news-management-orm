package com.epam.lab.news.repo;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.configuration.ApplicationConfig;
import com.epam.lab.news.data.repo.impl.BasePagingRepositoryImpl;
import com.epam.lab.news.util.TestClassListener;
import com.epam.lab.news.util.TestClassRunner;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

@RunWith(TestClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class AuthorRepositoryTest implements TestClassListener {
    @Autowired
    @Qualifier("AuthorRepository")
    BasePagingRepositoryImpl repository;

    @Override
    public void beforeClass() {}

    @Test
    public void exists(){
        Assert.assertEquals(false, repository.exists(0L));
        Assert.assertEquals(true, repository.exists(1L));
    }

    @Test
    public void saveAndDelete(){
        Author mocked = new Author();
        mocked.setName("Mock author");
        Author author = (Author)repository.save(mocked);
        Assert.assertEquals(mocked.getName(), author.getName());
        Assert.assertNotNull(author.getId());
        repository.delete(author);
        Assert.assertEquals(false, repository.exists(author.getId()));
    }

    @Test
    public void allAndCount(){
        List authors = repository.all();
        Assert.assertNotNull(authors);
        assertThat(authors.size(), is(repository.count().intValue()));
    }

}
