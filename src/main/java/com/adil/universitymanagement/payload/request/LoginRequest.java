package com.adil.universitymanagement.payload.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
