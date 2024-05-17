package com.adil.universitymanagement.payload.request;


import com.adil.universitymanagement.entity.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {

    private String username;
    private String password;
    private Role role;
}
