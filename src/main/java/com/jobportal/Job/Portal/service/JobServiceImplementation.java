package com.jobportal.Job.Portal.service;
import com.jobportal.Job.Portal.dto.ApplicantsDTO;
import com.jobportal.Job.Portal.dto.ApplicationStatus;
import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.entity.Applicants;
import com.jobportal.Job.Portal.entity.Job;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.JobRepository;
import com.jobportal.Job.Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("jobService")
public class JobServiceImplementation implements  JobService {

    @Autowired
    JobRepository jobRepository;
    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {
        jobDTO.setPostTime(LocalDateTime.now());
        jobDTO.setId(Utilities.generateSequence("jobs"));
        return jobRepository.save(jobDTO.toEntity()).toDto();
    }

    @Override
    public List<JobDTO> getAllPosts() throws JobPortalException {
        return jobRepository.findAll().stream().map((x)->x.toDto()).toList();
    }

    @Override
    public JobDTO getJobsById(Long id) throws JobPortalException {
        return jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND")).toDto();
    }

    @Override
    public void applyJob(Long id, ApplicantsDTO applicantsDTO) throws JobPortalException {
       Job job =jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
       List<Applicants> applicants=job.getApplicants();
       if(applicants==null){//null beacuse the array list is not created at all so
           applicants=new ArrayList<>();
       }
//       check if the applicant has applied already or not using filter
       if(applicants.stream().filter((x)->x.getApplicantId()==applicantsDTO.getApplicantId()).toList().size()>0)throw  new JobPortalException("JOB_ALREADY_APPLIED");
       applicantsDTO.setApplicationStatus(ApplicationStatus.APPLIED);
       applicants.add(applicantsDTO.toEntity());
       job.setApplicants(applicants);
       jobRepository.save(job);
    }
}
