package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping(value = "/posts")
    @ResponseBody
    public String index() {return "<h1>Posts index page</h1>";}

    @GetMapping(value = "/posts/{id}")
    @ResponseBody
    public String onePost() {return "<h1>View an individual post</h1>";}

    @GetMapping(value = "/posts/create")
    @ResponseBody
    public String createView() {return "<h1>View the form for creating a post</h1>";}

    @PostMapping(value = "/posts/create")
    @ResponseBody
    public String createPost() {return "<h1>Create a new post</h1>";}
}
