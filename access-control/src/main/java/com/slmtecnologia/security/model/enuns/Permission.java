package com.slmtecnologia.security.model.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    PERSON_READ("person:read"),
    PERSON_UPDATE("person:update"),
    PERSON_CREATE("person:create"),
    PERSON_DELETE("person:delete"),

    USER_READ("person:read"),
    USER_UPDATE("person:update"),
    USER_CREATE("person:create"),
    USER_DELETE("person:delete")

    ;

    @Getter
    private final String permission;
}
