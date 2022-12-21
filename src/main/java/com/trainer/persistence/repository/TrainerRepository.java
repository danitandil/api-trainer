package com.trainer.persistence.repository;

import com.trainer.persistence.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    public Optional<Trainer> getByEmail(String email);
}
