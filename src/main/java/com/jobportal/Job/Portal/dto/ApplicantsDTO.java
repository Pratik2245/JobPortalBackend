package com.jobportal.Job.Portal.dto;

import com.jobportal.Job.Portal.entity.Applicants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantsDTO {
    private String name;
    private String email;
    private String phone;
    private String resume;
    private String coverLetter;
    private String website;
    private Long applicantId;
    private LocalDateTime timeStramp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;
    public Applicants toEntity(){
        return new Applicants(
                this.name,
                this.email,
                this.phone,
                this.resume!=null? Base64.getDecoder().decode(this.resume):null,
                this.coverLetter,
                this.website,
                this.applicantId,
                this.timeStramp,
                this.applicationStatus,
                this.interviewTime
        );
    }
}
