package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.entity.Otp;
import com.jobportal.Job.Portal.entity.User;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.OtpRepository;
import com.jobportal.Job.Portal.repository.UserRepository;
import com.jobportal.Job.Portal.utility.Utilities;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    OtpRepository otpRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public UserDTO registerUser( UserDTO userDTO) throws JobPortalException {
        Optional<User> optionalUser=userRepository.findByEmail(userDTO.getEmail());
        if(optionalUser.isPresent())throw new JobPortalException("USER_FOUND");
        userDTO.setId(Utilities.generateSequence("users"));
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));//encoding the password
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        if(!encoder.matches(loginDTO.getPassword(),user.getPassword())){
            throw new JobPortalException("INVALID_CREDENTIALS");
        }
        return user.toDTO();
    }

    @Override
    public boolean sendOtp(String email) throws JobPortalException, MessagingException {
        userRepository.findByEmail(email).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
//        MimeMessage is used when you want to send rich or complex email content — like HTML, attachments, inline images, or multipart content — instead of just plain text.
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setTo(email);
        helper.setSubject("Your Otp Code");
        String generated=Utilities.generateOtp();
        Otp otp=new Otp(email,generated, LocalDateTime.now());
//        Insert or Update (Upsert) .save method
        otpRepository.save(otp);
//        setting html is present in the above text true or false
        helper.setText("Your Code is:"+generated,false);
        mailSender.send(mimeMessage);
        return true;

    }


}
