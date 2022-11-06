package by.rusak.service;

import by.rusak.domain.Mail;
import by.rusak.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void sendSimpleMessage(final Mail mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());

        emailSender.send(message);
    }

    public void sendActivationCode (User user) {
        Mail mail = new Mail();
        mail.setFrom(username);
        mail.setTo(user.getEmail());
        mail.setSubject("Verify registration");
        mail.setContent("Click the link below to verify your registration: " +
                "http://localhost:8080/registration/" + user.getActivationCode());
        sendSimpleMessage(mail);
    }
}
