package com.trainer.mapper;

import com.trainer.persistence.entity.Trainer;
import com.trainer.service.dto.TrainerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainerDTOToTrainerTest {

    @InjectMocks
    TrainerDTOToTrainer mapper;

    TrainerDTO trainerDTO;
    private Long id = 1L;
    private String email = "abc@mail.com";
    private String phone = "1423425234";
    private String firstName = "John";
    private String lastName= "Doe";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trainerDTO = new TrainerDTO(id, email, phone, firstName, lastName);
    }

    @Test
    void testMap() {
        Trainer trainerResult = mapper.map(trainerDTO);

        Trainer expectedTrainer = new Trainer();
        expectedTrainer.setId(trainerDTO.getId());
        expectedTrainer.setEmail(trainerDTO.getEmail());
        expectedTrainer.setPhone(trainerDTO.getPhone());
        expectedTrainer.setFirstName(trainerDTO.getFirstName());
        expectedTrainer.setLastName(trainerDTO.getLastName());

        assertEquals(expectedTrainer, trainerResult);
    }

}
