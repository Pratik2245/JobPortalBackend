package com.jobportal.Job.Portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    private Long id;
    private Long applicantId;
    private LocalDateTime interViewTime;
    private ApplicationStatus applicationStatus;
}
