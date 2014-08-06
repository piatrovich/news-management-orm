package com.epam.lab.news.controller;

import com.epam.lab.news.configuration.ApplicationConfig;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Test for ViewsController
 */
@RunWith(TestClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ViewsControllerTest implements TestClassListener {
    /** Application context */
    @Autowired
    WebApplicationContext context;

    /** Entry point */
    static MockMvc mockMvc;

    /**
     * Initializing mock
     */
    @Override
    public void beforeClass() {
        mockMvc = webAppContextSetup(context).build();
    }

    /**
     * Testing index page
     *
     * @throws Exception
     */
    @Test
    public void testIndex() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/index.jsp"));
    }

    /**
     * Testing view news page
     *
     * @throws Exception
     */
    @Test
    public void testShowView() throws Exception{
        mockMvc.perform(get("/view/2345"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/view.jsp"));
    }

    /**
     * Testing add news page
     *
     * @throws Exception
     */
    @Test
     public void testShowAdd() throws Exception{
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/add.jsp"));
    }

    /**
     * Testing edit news page
     *
     * @throws Exception
     */
    @Test
     public void testShowEdit() throws Exception{
        mockMvc.perform(get("/edit"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/edit.jsp"));
    }

    /**
     * Testing not existing page
     *
     * @throws Exception
     */
    @Test
    public void testNotFond() throws Exception{
        mockMvc.perform(get("/imaginary_page"))
                .andExpect(status().isNotFound());
    }

}
