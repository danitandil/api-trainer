package com.trainer.mapper;


import com.trainer.persistence.entity.Trainer;
import com.trainer.services.dto.TrainerInDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainerInDTOtoTrainer implements IMapper<TrainerInDTO, Trainer>{

    @Override
    public Trainer map(TrainerInDTO in) {
        Trainer trainer = new Trainer();
        trainer.setEmail(in.getEmail());
        trainer.setPhone(in.getPhone());
        trainer.setFirstName(in.getFirstName());
        trainer.setLastName(in.getLastName());
        return trainer;
    }
}