package by.rusak.controller.requests;

import lombok.Data;

@Data
public class AuthRequest {

    private String login;

    private String password;
}
