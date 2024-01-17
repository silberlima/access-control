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
    USER_DELETE("user:delete"),

    ROLE_READ("role:read"),
    ROLE_UPDATE("role:update"),
    ROLE_CREATE("role:create"),
    ROLE_DELETE("role:delete"),

    PERMISSION_READ("permission:read"),
    PERMISSION_UPDATE("permission:update"),
    PERMISSION_CREATE("permission:create"),
    PERMISSION_DELETE("permission:delete"),

    APPLICATION_READ("application:read"),
    APPLICATION_UPDATE("application:update"),
    APPLICATION_CREATE("application:create"),
    APPLICATION_DELETE("application:delete"),

    ;

    @Getter
    private final String permission;
}
