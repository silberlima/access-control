package com.slmtecnologia.config.security.auth;

import com.slmtecnologia.config.security.user.Role;
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
    private Role role;
}
