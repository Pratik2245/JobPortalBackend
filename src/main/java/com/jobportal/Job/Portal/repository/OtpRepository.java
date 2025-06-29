package com.jobportal.Job.Portal.repository;

import com.jobportal.Job.Portal.entity.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<Otp,String> {

}
