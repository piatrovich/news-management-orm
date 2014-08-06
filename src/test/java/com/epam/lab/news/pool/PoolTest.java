package com.epam.lab.news.pool;

import com.epam.lab.news.configuration.ApplicationConfig;
import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class PoolTest {
    /** Application context */
    @Autowired
    protected WebApplicationContext context;

    /** Wiring pool */
    @Autowired
    ConnectionPool pool;

    /**
     * Checking pool size behavior
     *
     * @throws Exception if test failed
     */
    @Test
    public void testConnection() throws Exception{
        int size = pool.size();
        Connection connection = pool.getConnection();
        Assert.assertEquals(size - 1, pool.size());
        Connection connection1 = pool.getConnection();
        Assert.assertEquals(size - 2, pool.size());
        pool.returnConnection(connection);
        Assert.assertEquals(size - 1, pool.size());
    }

}
