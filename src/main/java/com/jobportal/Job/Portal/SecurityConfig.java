package com.jobportal.Job.Portal;

import com.jobportal.Job.Portal.jwt.JwtAuthenticationEntryPoint;
import com.jobportal.Job.Portal.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    JwtAuthenticationEntryPoint point;
    @Autowired
    JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HttpServlet httpServlet) throws Exception {
//        httpSecurity.authorizeHttpRequests((req)->req.requestMatchers("/**").permitAll().anyRequest().authenticated());
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        return httpSecurity.build();
        httpSecurity
        .cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/auth/login","/users/register","/users/sendOtp/**","/users/verifyOtp/**","users/changePassword/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
