package com.jobportal.Job.Portal.dto;

import com.jobportal.Job.Portal.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
//    private byte[] image;
    private String company;
    private String location;
    private String about;
    private List<String> skills;
    private List<Experience> experiences;
    private List<Certification> certifications;
    private List<Long> savedJobs;
    public Profile toEntity(){
        return new Profile(this.id,this.name,this.email,this.jobTitle,this.company,this.location,this.about,this.skills,this.experiences,this.certifications,this.savedJobs);
    }
}
