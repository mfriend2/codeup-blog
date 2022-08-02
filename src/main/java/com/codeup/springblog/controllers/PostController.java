package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String index() {return "<h1>Posts index page</h1>";}

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String onePost() {return "<h1>View an individual post</h1>";}

    @RequestMapping(value = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createView() {return "<h1>View the form for creating a post</h1>";}

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost() {return "<h1>Create a new post</h1>";}
}
