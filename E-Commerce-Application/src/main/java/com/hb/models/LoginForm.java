package com.hb.models;

import lombok.Data; 
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginForm {
    private String username;
    private String password;

}

