package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResultsController {

    @GetMapping("/roll-dice/{a}")
    @ResponseBody
    public String result(@PathVariable int a) {
        int randomNum = (int) (Math.random() * 6) + 1;
        String correct = String.format("You guessed: %d<br>The number rolled was: %d<br>You guessed correct.", a, randomNum);
        String incorrect = String.format("You guessed: %d<br>The number rolled was: %d<br>You guessed incorrect.", a, randomNum);
        if (a == randomNum) {
           return  correct;
        } else {
            return incorrect;
        }
    }
}
