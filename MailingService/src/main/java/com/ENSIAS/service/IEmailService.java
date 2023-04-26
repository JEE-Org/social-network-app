package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.Email;
import org.springframework.stereotype.Service;

@Service
public interface IEmailService {
    //String sendEmail(Email email);
    String sendEmail(ENSIASt ensiaSt);
}
