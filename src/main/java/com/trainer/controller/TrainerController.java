package com.trainer.controller;

import com.trainer.services.TrainerService;
import com.trainer.services.dto.TrainerInDTO;
import com.trainer.persistence.entity.Trainer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody @Valid TrainerInDTO trainerInDTO) {
        return new ResponseEntity<>(this.trainerService.createTrainer(trainerInDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> findAll(){
        return new ResponseEntity<>(this.trainerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Trainer> findById(@PathVariable("id") @NotEmpty Long id) {
        var trainer = this.trainerService.findById(id);
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }
}
