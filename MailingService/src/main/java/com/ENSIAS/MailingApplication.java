package com.ENSIAS;


import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.Email;
import com.ENSIAS.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MailingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailingApplication.class,args);
    }

//    @Autowired
//    private EmailService emailService;
//
//    Email email = new Email(
//            "haytamelhayani@gmail.com",
//            "BODY",
//            "Subjeect"
//    );
//    ENSIASt ensiaSt = new ENSIASt(
//            "haitam",
//            "elhayni",
//            "haytamelhayani@gmail.com"
//    );
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendEmail(){
//        emailService.sendEmail(ensiaSt);
//    }
}