package com.trainer.controller;

import com.trainer.exception.TrainerNotFound;
import com.trainer.mapper.TrainerDTOToTrainer;
import com.trainer.mapper.TrainerToTrainerDTO;
import com.trainer.persistence.entity.Trainer;
import com.trainer.service.TrainerService;
import com.trainer.service.dto.TrainerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TrainerControllerTest {
    @InjectMocks
    TrainerController trainerController;

    @Mock
    TrainerService trainerService;

    @Mock
    TrainerToTrainerDTO trainerToTrainerDTO;

    @Mock
    TrainerDTOToTrainer trainerDTOtoTrainer;

    TrainerDTO trainerDTOWithoutId;
    TrainerDTO emptyTrainerDTO;
    Trainer trainerWithoutId;
    TrainerDTO trainerDTO;
    Trainer trainer;

    private Long id = 1L;
    private String email = "abc@mail.com";
    private String phone = "1423425234";
    private String firstName = "John";
    private String lastName= "Doe";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        emptyTrainerDTO = new TrainerDTO(null, "", "", "", "");

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

        trainerDTO = new TrainerDTO(id, email, phone, firstName, lastName);
        trainerDTOWithoutId = new TrainerDTO(null, email, phone, firstName, lastName);
    }

    @Test
    void testFindByIdSuccess() {
        when(trainerDTOtoTrainer.map(trainerDTOWithoutId)).thenReturn(trainerWithoutId);
        when(trainerService.findById(id)).thenReturn(trainer);
        when(trainerToTrainerDTO.map(trainer)).thenReturn(trainerDTO);

        ResponseEntity<TrainerDTO> response = trainerController.findById(id);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), trainerDTO);
    }

    @Test
    void testFindByIdNotFoundNotExistId() {
        when(trainerDTOtoTrainer.map(trainerDTOWithoutId)).thenReturn(trainerWithoutId);
        when(trainerService.findById(id)).thenThrow(new TrainerNotFound("Trainer not found with id " + id));
        when(trainerToTrainerDTO.map(trainer)).thenReturn(trainerDTO);
        ResponseEntity<TrainerDTO> response;
        try {
            response = trainerController.findById(id);
        } catch (TrainerNotFound exception) {
            assertEquals(exception.getMessage(), "Trainer not found with id " + id);
        }
    }

    @Test
    void testFindByIdNotFoundEmptyId() {
        ResponseEntity<TrainerDTO> response;
        try {
            response = trainerController.findById(null);
        } catch (TrainerNotFound exception) {
            assertEquals(exception.getMessage(), "Trainer not found with id " + id);
        }
    }

    @Test
    void testFindAllSuccess() {
        when(trainerToTrainerDTO.map(trainer)).thenReturn(trainerDTO);
        when(trainerService.findAll()).thenReturn(Arrays.asList(trainer));
        ResponseEntity<List<TrainerDTO>> response = trainerController.findAll();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), Arrays.asList(trainerDTO));
    }

    @Test
    void testCreateTrainerSuccess() {
        when(trainerDTOtoTrainer.map(trainerDTOWithoutId)).thenReturn(trainerWithoutId);
        when(trainerService.createTrainer(trainerWithoutId)).thenReturn(trainer);
        when(trainerToTrainerDTO.map(trainer)).thenReturn(trainerDTO);
        ResponseEntity<TrainerDTO> response = trainerController.createTrainer(trainerDTOWithoutId);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), trainerDTO);
    }

}
