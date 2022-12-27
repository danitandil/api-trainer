package com.trainer.controller;

import com.trainer.mapper.TrainerDTOToTrainer;
import com.trainer.mapper.TrainerToTrainerDTO;
import com.trainer.persistence.entity.Trainer;
import com.trainer.service.TrainerService;
import com.trainer.service.dto.TrainerDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerDTOToTrainer mapperToTrainer;

    @Autowired
    private TrainerToTrainerDTO mapperToTrainerDTO;

    @PostMapping
    public ResponseEntity<TrainerDTO> createTrainer(@RequestBody @Valid TrainerDTO trainerDTO) {
        Trainer trainer = mapperToTrainer.map(trainerDTO);
        Trainer response = this.trainerService.createTrainer(trainer);
        TrainerDTO responseDTO = mapperToTrainerDTO.map(response);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrainerDTO>> findAll(){
        List<TrainerDTO> trainerDTOs = this.trainerService.findAll().stream().map(trainer -> mapperToTrainerDTO.map(trainer)).collect(Collectors.toList());
        return new ResponseEntity<>(trainerDTOs, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TrainerDTO> findById(@PathVariable("id") @NotEmpty Long id) {
        Trainer trainer = this.trainerService.findById(id);
        TrainerDTO response = mapperToTrainerDTO.map(trainer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
