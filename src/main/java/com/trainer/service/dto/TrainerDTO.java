package com.trainer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainerDTO {

    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
