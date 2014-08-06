package com.epam.lab.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Describes main RESTful application controller
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@Controller
public class ViewsController {

    /**
     * Handles requests for main page
     *
     * @return index
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex() {
		return "index";
	}

    /**
     * Handles requests for viewing page
     *
     * @return view
     */
    @RequestMapping(value = "/view/**", method = RequestMethod.GET)
    public String showView() {
        return "view";
    }

    /**
     * Handles requests for adding page
     *
     * @return add
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAdd() {
        return "add";
    }

    /**
     * Handles requests for editing page
     *
     * @return edit
     */
    @RequestMapping(value = "/edit/**", method = RequestMethod.GET)
    public String showEdit() {
        return "edit";
    }

}