package com.jobportal.Job.Portal.entity;

import com.jobportal.Job.Portal.dto.ApplicantsDTO;
import com.jobportal.Job.Portal.dto.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicants {
    private String name;
    private String email;
    private String phone;
    private byte[] resume;
    private String coverLetter;
    private String website;
    private Long applicantId;
    private LocalDateTime timeStramp;
    private ApplicationStatus applicationStatus;
    public ApplicantsDTO toDTO(){
        return new ApplicantsDTO(
                this.name,
                this.email,
                this.phone,
                this.resume!=null? Base64.getEncoder().encodeToString(this.resume):null,
                this.coverLetter,
                this.website,
                this.applicantId,
                this.timeStramp,
                this.applicationStatus
        );
    }
}
