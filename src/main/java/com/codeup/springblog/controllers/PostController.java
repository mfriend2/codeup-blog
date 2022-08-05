package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostRepository postsDao;
    private UserRepository usersDao;

    public PostController(PostRepository postsDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
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
        User newUser = new User("new", "new@email.com", "password");
        Post newPost = new Post(title, body, newUser);
        usersDao.save(newUser);
        postsDao.save(newPost);
        return "redirect:/posts";
    }
}
