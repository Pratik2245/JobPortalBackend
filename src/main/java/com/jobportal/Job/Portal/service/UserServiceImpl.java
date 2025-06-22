package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.entity.User;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.repository.UserRepository;
import com.jobportal.Job.Portal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;
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
}
