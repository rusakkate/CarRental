package by.rusak.security;

import by.rusak.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@AllArgsConstructor
@Component
public class AuthUserInformation {
    private final UserService userService;

    public String getAuthUserLogin (){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String login = principal.getUsername();
        return login;
    }

    public Long getAuthUserId (){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String login = principal.getUsername();
        Long idUser = userService.findByCredentialsLogin(login).get().getId();
        return idUser;
    }
}
