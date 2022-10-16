package by.rusak.controller.requests;

import lombok.Data;

@Data
public class UserChangeRequest extends UserCreateRequest {

    private Long id;
}