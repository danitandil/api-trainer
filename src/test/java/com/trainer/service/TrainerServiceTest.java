package com.trainer.service;

import com.trainer.exception.EmailDuplicatedException;
import com.trainer.exception.TrainerNotFound;
import com.trainer.persistence.entity.Trainer;
import com.trainer.persistence.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TrainerServiceTest {
    @InjectMocks
    TrainerService trainerService;

    @Mock
    TrainerRepository repository;
    Trainer trainerWithoutId;
    Trainer trainer;
    Trainer emptyTrainer;

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

        trainerWithoutId = new Trainer();
        trainerWithoutId.setEmail(email);
        trainerWithoutId.setPhone(phone);
        trainerWithoutId.setFirstName(firstName);
        trainerWithoutId.setLastName(lastName);

        emptyTrainer = new Trainer();
    }

    @Test
    public void createTrainerSuccess() {
        when(repository.save(trainerWithoutId)).thenReturn(trainer);
        Trainer createdTrainer = trainerService.createTrainer(trainerWithoutId);
        assertEquals(trainer, createdTrainer);
    }

    @Test
    public void createTrainerErrorEmpty() {
        try {
            Trainer createdTrainer = trainerService.createTrainer(emptyTrainer);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "");
        }
    }
    @Test
    public void createTrainerEmailDuplicated() {
        when(repository.save(trainerWithoutId)).thenReturn(trainer);
        when(repository.getByEmail(email)).thenReturn(Optional.of(trainer));
        try {
            trainerService.createTrainer(trainerWithoutId);
        } catch (EmailDuplicatedException ex) {
            assertEquals(ex.getMessage(), "A trainer with email address " + email + " already exists.");
        }
    }
    @Test
    public void findByIdSuccess() {
        when(repository.findById(id)).thenReturn(Optional.of(trainer));
        Trainer requestedTrainer = trainerService.findById(id);
        assertEquals(trainer, requestedTrainer);
    }

    @Test
    public void findByIdNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        try {
            Trainer requestedTrainer = trainerService.findById(id);
        } catch (TrainerNotFound ex) {
            assertEquals(ex.getMessage(), "Trainer not found with id " + id);
        }
    }

    @Test
    public void findAllSuccess() {
        when(repository.findAll()).thenReturn(Arrays.asList(trainer));
        List<Trainer> trainers = trainerService.findAll();
        assertEquals(Arrays.asList(trainer), trainers);
    }
}
