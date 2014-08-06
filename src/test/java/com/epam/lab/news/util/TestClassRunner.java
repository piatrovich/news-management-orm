package com.epam.lab.news.util;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Custom implementation of runner
 *
 * @author Dzmitry Piatrovich
 */
public class TestClassRunner extends SpringJUnit4ClassRunner {
    private TestClassListener classListener;

    /**
     * Constructor
     *
     * @param clazz Test class
     * @throws InitializationError
     */
    public TestClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    /**
     * Creates test object only once
     *
     * @return Object test
     * @throws Exception
     */
    @Override
    protected Object createTest() throws Exception {
        Object test =  super.createTest();
        if(test instanceof TestClassListener && classListener == null){
            classListener = (TestClassListener)test;
            classListener.beforeClass();
        }
        return test;
    }

}
