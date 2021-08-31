package com.midorlo.wolkenbruch.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountLoginDto {

   private String  accountname;
   private String  password;
   private String  email;
   private Boolean rememberMe;
}
