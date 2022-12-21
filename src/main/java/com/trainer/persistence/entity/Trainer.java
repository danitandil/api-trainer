package com.trainer.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
