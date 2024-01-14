package com.slmtecnologia.service;

import com.slmtecnologia.model.dto.EmailRequest;
import jakarta.mail.MessagingException;

public interface IEmailService {

    void sendEmailToReset(EmailRequest email) throws MessagingException;
}
