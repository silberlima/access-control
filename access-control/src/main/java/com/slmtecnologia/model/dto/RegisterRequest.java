package com.slmtecnologia.model.dto;

import com.slmtecnologia.model.enuns.Role;

public record RegisterRequest(String firstName, String lastname, String email,String password,Role role) {

}
