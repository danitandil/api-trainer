package com.trainer.service;

import com.trainer.exception.EmailDuplicatedException;
import com.trainer.exception.TrainerNotFound;
import com.trainer.persistence.entity.Trainer;
import com.trainer.persistence.repository.TrainerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository repository;

    private void checkEmailDuplication(String email) {
        if(this.repository.getByEmail(email).isPresent()){
            throw new EmailDuplicatedException("A trainer with email address " + email + " already exists.");
        }
    }
    public Trainer createTrainer(@Valid Trainer trainer) {
        checkEmailDuplication(trainer.getEmail());
        return this.repository.save(trainer);
    }

    public List<Trainer> findAll() {
        return this.repository.findAll();
    }

    public Trainer findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new TrainerNotFound("Trainer not found with id " + id));
    }
}