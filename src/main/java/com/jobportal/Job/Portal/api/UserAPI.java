package com.jobportal.Job.Portal.api;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@Validated
@RequestMapping("/users")
public class UserAPI {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        userDTO=userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.loginUser(loginDTO),HttpStatus.OK);
    }
}
