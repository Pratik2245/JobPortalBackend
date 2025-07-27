package com.jobportal.Job.Portal.dto;

import com.jobportal.Job.Portal.entity.Job;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    Long id;
    private String jobTitle;
    private String company;
    private List<Applicants> applicants;
    private String about;
    private String experience;
    private String jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime postTime;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    public Job toEntity(){
        return new Job(this.id,this.jobTitle,this.company,this.applicants,this.about,this.experience,this.jobType,this.location,this.packageOffered,this.postTime,this.skillsRequired,this.jobStatus);
    }
}
