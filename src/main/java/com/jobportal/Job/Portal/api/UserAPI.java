package com.jobportal.Job.Portal.api;

import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserAPI {

    @Autowired
    private UserService userService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        userDTO=userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }
}
