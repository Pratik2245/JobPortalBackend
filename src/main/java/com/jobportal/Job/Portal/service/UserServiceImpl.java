package com.jobportal.Job.Portal.service;

import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.entity.User;
import com.jobportal.Job.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service(value = "userService")
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDTO registerUser( UserDTO userDTO) {
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }
}
