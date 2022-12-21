package com.trainer.services.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TrainerInDTO {
    @NotEmpty(message = "Email is Required")
    @Email(message = "Email is not in a correct format")
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty(message = "First name is Required")
    private String firstName;
    @NotEmpty(message = "Last name is Required")
    private String lastName;
}
