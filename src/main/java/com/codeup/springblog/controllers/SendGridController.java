package com.codeup.springblog.controllers;

import com.codeup.springblog.services.SendGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class SendGridController {

    @Autowired
    SendGridService sendGridService;

    @PostMapping("send/text")
    public String send() throws IOException {
        return sendGridService.sendEmail();
    }
}
