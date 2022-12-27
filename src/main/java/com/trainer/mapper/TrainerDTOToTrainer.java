package com.trainer.mapper;

import com.trainer.persistence.entity.Trainer;
import com.trainer.service.dto.TrainerDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainerDTOToTrainer implements IMapper<TrainerDTO, Trainer> {

    @Override
    public Trainer map(TrainerDTO in) {
        Trainer trainer = new Trainer();
        trainer.setId(in.getId());
        trainer.setEmail(in.getEmail());
        trainer.setPhone(in.getPhone());
        trainer.setFirstName(in.getFirstName());
        trainer.setLastName(in.getLastName());
        return trainer;
    }
}