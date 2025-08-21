package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.ApplicantsDTO;
import com.jobportal.Job.Portal.dto.Application;
import com.jobportal.Job.Portal.dto.JobDTO;
import com.jobportal.Job.Portal.dto.ResponseDTO;
import com.jobportal.Job.Portal.entity.Job;
import com.jobportal.Job.Portal.exception.JobPortalException;

import java.util.List;

public interface JobService {
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException;
    public List<JobDTO> getAllPosts() throws JobPortalException;
    public JobDTO getJobsById(Long id) throws  JobPortalException;

    void applyJob(Long id, ApplicantsDTO applicantsDTO) throws  JobPortalException;
    public List<JobDTO> getJobsPostedBy(Long id) throws  JobPortalException;

    void changeApplicationStatus(Application application) throws  JobPortalException;
}
