package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private Post post = new Post(1, "Hello", "This is a new post.");
    private Post post2 = new Post(2, "Bye", "This is the last post.");
    private List<Post> posts = new ArrayList<Post>();

    public PostController() {
        posts.add(post);
        posts.add(post2);
    }

    @GetMapping(value = "/posts")
    public String index(Model model) {
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping(value = "/posts/{id}")
    public String onePost(@PathVariable long id, Model model) {
        model.addAttribute("posts", posts);
        model.addAttribute("id", id);
        return "posts/show";
    }

    @GetMapping(value = "/posts/create")
    @ResponseBody
    public String createView() {
        return "<h1>View the form for creating a post</h1>";
    }

    @PostMapping(value = "/posts/create")
    @ResponseBody
    public String createPost() {
        return "<h1>Create a new post</h1>";
    }

    public static void main(String[] args) {

    }
}
