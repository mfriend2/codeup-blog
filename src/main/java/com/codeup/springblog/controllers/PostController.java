package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String createView(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping(value = "/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User newUser = new User("new", "new@email.com", "password");
        usersDao.save(newUser);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model, HttpSession session) {
        session.setAttribute("id", id);
        model.addAttribute("post", postsDao.getById(id));
        return "posts/edit";
    }

    @PostMapping(value = "/posts/edit")
    public String submitEdit(@ModelAttribute("post") Post post, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        postsDao.updatePost(id, post.getTitle(), post.getBody());
        return "redirect:/posts";
    }
}
