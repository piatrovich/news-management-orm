package com.epam.lab.news.api;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.configuration.ApplicationConfig;
import com.epam.lab.news.controller.APIController;
import com.epam.lab.news.util.TestClassListener;
import com.epam.lab.news.util.TestClassRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Tests for
 *
 * @author Dzmitry Piatrovich
 */
@RunWith(TestClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class APITest implements TestClassListener {
    /** Application context */
    @Autowired
    protected WebApplicationContext context;

    /** Wiring API controller */
    @Autowired
    protected APIController apiController;

    /** Entry point */
    private static MockMvc mockMvc;

    @Override
    public void beforeClass() {
        mockMvc = webAppContextSetup(context).build();
    }

    /**
     * Checks content type and response code
     *
     * @throws Exception
     */
    @Test
    public void testGetAll() throws Exception{
        mockMvc.perform(get("/api/all"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    /**
     * Checks response code if empty request sent
     *
     * @throws Exception
     */
    @Test
    public void testAddArticle() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/add").content(mapper.writer()
                .writeValueAsString(new Article())).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().encoding("UTF-8"))
                .andExpect(status().isOk());
    }

}
