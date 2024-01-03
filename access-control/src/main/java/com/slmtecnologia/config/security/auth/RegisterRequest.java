package com.slmtecnologia.config.security.auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String firstName;
    private String lastname;
    private String email;
    private String password;
}
