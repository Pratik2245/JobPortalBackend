package com.jobportal.Job.Portal.api;

import com.jobportal.Job.Portal.dto.ApplicantsDTO;
import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.dto.ResponseDTO;
import com.jobportal.Job.Portal.entity.Job;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/job")
public class JobApi {
    @Autowired
    JobService jobService;
    @PostMapping("/post")
    public ResponseEntity<JobDTO> postJobs(@RequestBody @Valid JobDTO jobDTO) throws JobPortalException {
       return new ResponseEntity<>(jobService.postJob(jobDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<List<JobDTO>> getAllJobs()throws  JobPortalException{
        return new ResponseEntity<>(jobService.getAllPosts(),HttpStatus.OK);
    }
    @GetMapping("/getAllJobs/{id}")
    public ResponseEntity<JobDTO> getJobsById(@PathVariable Long id)throws  JobPortalException{
        return new ResponseEntity<>(jobService.getJobsById(id),HttpStatus.OK);
    }
    @PostMapping("/apply/{id}")
    public ResponseEntity<ResponseDTO> applyJob(@PathVariable Long id, @RequestBody ApplicantsDTO applicantsDTO) throws JobPortalException {
        jobService.applyJob(id,applicantsDTO);
        return  new ResponseEntity<>(new ResponseDTO("Applied Successfully"),HttpStatus.OK);
    }

}
