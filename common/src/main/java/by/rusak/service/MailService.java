package by.rusak.service;

import by.rusak.domain.Mail;

import javax.validation.constraints.NotNull;

public interface MailService {

    void sendActiveMail(@NotNull Mail mail);
}