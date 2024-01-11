package com.slmtecnologia.model.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    PERSON_READ("person:read"),
    PERSON_UPDATE("person:update"),
    PERSON_CREATE("person:create"),
    PERSON_DELETE("person:delete"),

    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete")

    ;

    @Getter
    private final String permission;
}
