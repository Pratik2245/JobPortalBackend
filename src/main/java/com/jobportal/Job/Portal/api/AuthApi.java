package com.jobportal.Job.Portal.api;

import com.jobportal.Job.Portal.jwt.AuthenticationRequest;
import com.jobportal.Job.Portal.jwt.AuthenticationResponse;
import com.jobportal.Job.Portal.jwt.JwtHelper;
import com.jobportal.Job.Portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthApi {
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private JwtHelper jwtHelper;
  @Autowired
  private AuthenticationManager authenticationManager;
  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
    final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());
    final String jwt=jwtHelper.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
