package com.codeup.springblog.services;

import com.sendgrid.*;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("sendGrid")
public class SendGridService {
    private static final Logger logger = LoggerFactory.getLogger(SendGridService.class);
    @Value("${SendGrid}")
    private static String apiKey;
    public String sendEmail() throws IOException {
        Email from = new Email("michael.l.friend2@gmail.com");
        String subject = "The Subject";
        Email to = new Email("mikelfriend2@gmail.com");
        Content content = new Content("text/plain", "This is a test email.");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException e) {
            throw e;
        }
    }
}
