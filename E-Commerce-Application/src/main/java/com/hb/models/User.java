package com.hb.models;

import java.time.LocalDate;  

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {
   private String username;
  
   private String password;
   
   private String mobileNumber;
  
   private String email;
  
   private String role;
   
   private LocalDate dateOfCreation = LocalDate.now();

}
