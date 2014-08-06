package com.epam.lab.news.exception;

import com.epam.lab.news.configuration.ApplicationConfig;
import com.epam.lab.news.controller.APIController;
import com.epam.lab.news.util.TestClassListener;
import com.epam.lab.news.util.TestClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Testing exception handling
 *
 * @author Dzmitry Piatrovich
 */
@RunWith(TestClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class GlobalExceptionHandlerTest implements TestClassListener {
    /** Application context */
    @Autowired
    WebApplicationContext context;

    /** Wiring controller */
    @Autowired
    protected APIController apiController;

    /** Entry point */
    MockMvc mockMvc;

    @Override
    public void beforeClass() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    /**
     * Checks exception handler for non existing news
     *
     * @throws Exception
     */
    @Test
    public void testGetArticleForView() throws Exception{
        mockMvc.perform(get("/api/get/0"))
                .andExpect(status().isNotFound());
    }

}
