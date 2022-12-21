package com.trainer.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String message;
    private String code;
}
