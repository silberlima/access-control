package com.slmtecnologia.security.model.dto;

import com.slmtecnologia.security.model.enuns.Role;
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
