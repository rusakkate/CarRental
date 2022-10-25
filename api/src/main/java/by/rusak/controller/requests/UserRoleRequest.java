package by.rusak.controller.requests;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserRoleRequest {

    private Long idRole;

    private Long idUser;
}
