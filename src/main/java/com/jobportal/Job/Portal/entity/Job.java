package com.jobportal.Job.Portal.entity;

import com.jobportal.Job.Portal.dto.Applicants;
import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.dto.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Job")
public class Job {
    @Id
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
    public JobDTO toDto(){
        return new JobDTO(this.id,this.jobTitle,this.company,this.applicants,this.about,this.experience,this.jobType,this.location,this.packageOffered,this.postTime,this.skillsRequired,this.jobStatus);
    }

}
