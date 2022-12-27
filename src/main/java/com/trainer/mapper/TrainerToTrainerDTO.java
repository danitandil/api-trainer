package com.trainer.mapper;

import com.trainer.persistence.entity.Trainer;
import com.trainer.service.dto.TrainerDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainerToTrainerDTO implements IMapper<Trainer, TrainerDTO> {
    @Override
    public TrainerDTO map(Trainer trainer) {
        TrainerDTO trainerDTO = new TrainerDTO(trainer.getId(), trainer.getEmail(), trainer.getPhone(), trainer.getFirstName(), trainer.getLastName());
        return trainerDTO;
    }
}
