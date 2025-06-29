package com.jobportal.Job.Portal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "Otp")
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
    @Id
    private String email;
    private String Otp;
    private LocalDateTime localDateTime;
}
