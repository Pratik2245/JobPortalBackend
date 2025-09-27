package com.jobportal.Job.Portal.service;
import com.jobportal.Job.Portal.dto.*;
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
     NotificationService notificationService;
    @Autowired
    JobRepository jobRepository;
    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {
        if(jobDTO.getId() == null || jobDTO.getId() == 0){
            jobDTO.setPostTime(LocalDateTime.now());
            jobDTO.setId(Utilities.generateSequence("jobs"));
            NotificationDTO notificationDTO=new NotificationDTO();
            notificationDTO.setAction("Job Posted Successfully");
            notificationDTO.setMessage("Job Posted Successfully for role: "+jobDTO.getJobTitle()+" at "+jobDTO.getCompany());
            notificationDTO.setUserId(jobDTO.getPostedBy());
            notificationDTO.setRoute("/posted-jobs/"+jobDTO.getId());
            try {
                notificationService.sendNotification(notificationDTO);
            } catch (JobPortalException e) {
                throw new RuntimeException(e);
            }
        }else{
           Job job=jobRepository.findById(jobDTO.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
           if(job.getJobStatus().equals(JobStatus.DRAFT) || jobDTO.getJobStatus().equals(JobStatus.CLOSED)){
               jobDTO.setPostTime(LocalDateTime.now());
           }
        }

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

    @Override
    public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException {
        return jobRepository.findByPostedBy(id).stream().map(x->x.toDto()).toList();
    }

    @Override
    public void changeApplicationStatus(Application application) throws JobPortalException {
        Job job =jobRepository.findById(application.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
        List<Applicants> list=job.getApplicants().stream().map((x)->{
            if(application.getApplicantId()==x.getApplicantId()){
                x.setApplicationStatus(application.getApplicationStatus());
                if(application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)){
                    x.setInterviewTime(application.getInterViewTime());
                    NotificationDTO notificationDTO=new NotificationDTO();
                    notificationDTO.setAction("Interview Scheduled");
                    notificationDTO.setMessage("Interview schedule for job id"+application.getId());
                    notificationDTO.setUserId(application.getApplicantId());
                    notificationDTO.setRoute("/job-history");
                    try {
                        notificationService.sendNotification(notificationDTO);
                    } catch (JobPortalException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return x;
        }).toList();
    job.setApplicants(list);
    jobRepository.save(job);

    }
}
