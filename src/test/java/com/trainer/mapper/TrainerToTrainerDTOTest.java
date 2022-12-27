package com.trainer.mapper;

import com.trainer.persistence.entity.Trainer;
import com.trainer.service.dto.TrainerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainerToTrainerDTOTest {
    @InjectMocks
    TrainerToTrainerDTO mapper;

    Trainer trainer;
    private Long id = 1L;
    private String email = "abc@mail.com";
    private String phone = "1423425234";
    private String firstName = "John";
    private String lastName= "Doe";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trainer = new Trainer();
        trainer.setId(id);
        trainer.setEmail(email);
        trainer.setPhone(phone);
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
    }

    @Test
    void testMap() {
        TrainerDTO trainerDTOResult = mapper.map(trainer);

        TrainerDTO expectedTrainerDTO = new TrainerDTO(
                trainer.getId(),
                trainer.getEmail(),
                trainer.getPhone(),
                trainer.getFirstName(),
                trainer.getLastName());

        assertEquals(expectedTrainerDTO, trainerDTOResult);
    }
}
