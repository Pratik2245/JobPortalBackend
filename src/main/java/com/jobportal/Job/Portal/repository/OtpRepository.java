package com.jobportal.Job.Portal.repository;

import com.jobportal.Job.Portal.entity.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OtpRepository extends MongoRepository<Otp,String> {
  List<Otp> findByLocalDateTimeBefore(LocalDateTime expiry);
}
