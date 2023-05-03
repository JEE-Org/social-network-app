package com.ENSIAS.controller;


import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.Email;
import com.ENSIAS.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/mailing")
public class MailingController {

    EmailService emailService;

    Email email = new Email(
            "haytamelhayani@gmail.com",
            "welcome to ENSIAS network",
            "ENSIAS Network"
    );

    @PostMapping("/sending")
    public String sendMail(@RequestBody ENSIASt ensiast){
        return emailService.sendEmail(ensiast);
    }
}
