package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public String sendEmail(ENSIASt ensiaSt) {
        String email = ensiaSt.getEmail();
        String body = "Dear,"+ensiaSt.getLastName()+" "+ensiaSt.getFirstName()+" \nWe welcome you for joining " +
                "our ENSIAS Network family ! \n"+"It's our pleasure to bring ENSIASts together ! \n"+
                "\nENSIASt un jour ENSIASt pour toujours";
        String subject = "Welcome to ENSIAS Network !";
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(email);
            mailMessage.setText(body);
            mailMessage.setSubject(subject);

            javaMailSender.send(mailMessage);
            return "Mail sent Successfully";
        }catch (Exception e){
            return "Error while Sending Mail";
        }
    }
}
