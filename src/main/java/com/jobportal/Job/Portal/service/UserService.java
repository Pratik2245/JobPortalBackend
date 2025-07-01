package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.ResponseDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;
import jakarta.mail.MessagingException;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

    public boolean sendOtp(String email) throws JobPortalException, MessagingException;

    public boolean verifyOtp(String email,String otp) throws  JobPortalException;

    public ResponseDTO changePassword(LoginDTO loginDTO)throws JobPortalException;
}
