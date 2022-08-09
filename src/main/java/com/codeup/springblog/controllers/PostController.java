package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.Tag;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
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
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(currentUser);
        postsDao.save(post);
        emailService.prepareAndSend(post, post.getTitle(), post.getBody());
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

    @GetMapping(value = "/posts/{id}/delete")
    public String deletePost(@ModelAttribute("post") Post post) {
        postsDao.delete(post);
        return "redirect:/posts";
    }

    @GetMapping(value = "/posts/tags")
    public String tags(Model model) {
        List<Post> posts = postsDao.findAll();
//        List<Post> postsThatAreFunny = new ArrayList<>();
//        for (Post post : posts) {
//            for (Tag tag: post.getTags()){
//                if(tag.getName().equals("Funny")) postsThatAreFunny.add(post);
//            }
//        }
        model.addAttribute("posts", posts);
        return "posts/tags";
    }
}
