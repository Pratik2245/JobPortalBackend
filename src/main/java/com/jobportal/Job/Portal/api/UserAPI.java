package com.jobportal.Job.Portal.api;

import com.jobportal.Job.Portal.dto.LoginDTO;
import com.jobportal.Job.Portal.dto.ResponseDTO;
import com.jobportal.Job.Portal.dto.UserDTO;
import com.jobportal.Job.Portal.exception.JobPortalException;
import com.jobportal.Job.Portal.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    @PostMapping(value = "/sendOtp/{email}")
    public ResponseEntity<?> sendOtp(@PathVariable  @Email(message = "Invalid email format") String email) throws JobPortalException, MessagingException {
        boolean b=userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDTO("OTP send successfully"),HttpStatus.OK);
    }
    @GetMapping(value = "/verifyOtp/{email}/{otp}")
    public ResponseEntity<?> verifyOtp(@PathVariable  @Email(message = "Invalid email format") String email, @PathVariable @Pattern(regexp = "\\d{6}", message = "OTP must be 6 digits") String otp) throws JobPortalException {
        boolean b=userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDTO("OTP Verified successfully"),HttpStatus.OK);
    }
    //changing the password
    @PostMapping(value = "/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.changePassword(loginDTO),HttpStatus.OK);
    }
}
