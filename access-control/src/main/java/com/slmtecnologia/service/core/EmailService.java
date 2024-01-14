package com.slmtecnologia.service.core;

import com.slmtecnologia.model.dto.EmailRequest;
import com.slmtecnologia.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmailToReset(EmailRequest email) throws MessagingException{
        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mail);
        message.setSubject(email.subject());
        message.setText(email.text(), true);
        message.setFrom(email.emailFrom());
        message.setTo(email.emailTo());

        mailSender.send(mail);
    }
}
