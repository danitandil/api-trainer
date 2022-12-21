package com.trainer.services;

import com.trainer.exceptions.EmailDuplicatedException;
import com.trainer.exceptions.TrainerNotFound;
import com.trainer.services.dto.TrainerInDTO;
import com.trainer.mapper.TrainerInDTOtoTrainer;
import com.trainer.persistence.entity.Trainer;
import com.trainer.persistence.repository.TrainerRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository repository;

    @Autowired
    private TrainerInDTOtoTrainer mapper;

    public Trainer createTrainer(TrainerInDTO trainerInDTO) {
        checkEmailDuplication(trainerInDTO.getEmail());
        Trainer trainer = mapper.map(trainerInDTO);
        return this.repository.save(trainer);
    }

    private void checkEmailDuplication(String email) {
        if(this.repository.getByEmail(email).isPresent()){
            throw new EmailDuplicatedException("A trainer with email address " + email + " already exists.");
        }
    }

    public List<Trainer> findAll() {
        return this.repository.findAll();
    }

    public Trainer findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new TrainerNotFound("Trainer not found with id " + id));
    }
}