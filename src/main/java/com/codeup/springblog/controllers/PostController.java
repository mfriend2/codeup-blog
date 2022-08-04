package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping(value = "/posts")
    public String index(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping(value = "/posts/{id}")
    public String onePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.getById(id));
        return "posts/show";
    }

    @GetMapping(value = "/posts/create")
    public String createView() {
        return "posts/create";
    }

    @PostMapping(value = "/posts/create")
    public String createPost(@RequestParam("title") String title, @RequestParam("body") String body) {
        Post newPost = new Post(title, body);
        postsDao.save(newPost);
        return "redirect:/posts";
    }

}
