package by.rusak.service;

import by.rusak.domain.Mail;
import by.rusak.domain.User;

public interface EmailService {

    void sendSimpleMessage(Mail mail);

    void sendActivationCode (User user);
}