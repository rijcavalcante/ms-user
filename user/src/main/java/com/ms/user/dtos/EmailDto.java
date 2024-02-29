package com.ms.user.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class EmailDto {
    private UUID userId;
    private String name;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDate registerDate;
}
