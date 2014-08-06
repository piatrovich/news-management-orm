package com.epam.lab.news.validation;

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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Tests article validator
 */
@RunWith(TestClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ArticleValidatorTest implements TestClassListener {
    /** Application context **/
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
     * Testing validator if title missed
     *
     * @throws Exception
     */
    @Test
     public void testMissingArticleTitle() throws Exception{
        Article article = new Article();
        article.setDescription("description");
        article.setText("text");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/add").content(mapper.writer()
                .writeValueAsString(article)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().encoding("UTF-8"))
                .andExpect(jsonPath("$.status", is(false)))
                .andExpect(jsonPath("$.result.['title.empty']", is("Title can not be empty")));
    }

    /**
     * Testing article validator if only title exists
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        Article article = new Article();
        article.setTitle("title");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/add").content(mapper.writer()
                .writeValueAsString(article)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().encoding("UTF-8"))
                .andExpect(jsonPath("$.status", is(false)))
                .andExpect(jsonPath("$.result.['description.empty']",
                        is("Description can not be empty")))
                .andExpect(jsonPath("$.status", is(false)))
                .andExpect(jsonPath("$.result.['text.empty']",
                        is("Text can not be empty")));
    }

}
