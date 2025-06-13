package com.jobportal.Job.Portal.dto;

import com.jobportal.Job.Portal.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String password;
    private AccountType accountType;

    public UserDTO(){

    }
    public UserDTO(String id, String name, String email, String password, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }
    public User toEntity(){
        return new User(this.id,this.name,this.email,this.password,this.accountType);
    }
}
