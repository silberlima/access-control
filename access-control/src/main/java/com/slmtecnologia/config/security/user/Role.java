package com.slmtecnologia.config.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.slmtecnologia.config.security.user.Permission.USER_CREATE;
import static com.slmtecnologia.config.security.user.Permission.USER_DELETE;
import static com.slmtecnologia.config.security.user.Permission.USER_READ;
import static com.slmtecnologia.config.security.user.Permission.USER_UPDATE;
import static com.slmtecnologia.config.security.user.Permission.PERSON_CREATE;
import static com.slmtecnologia.config.security.user.Permission.PERSON_DELETE;
import static com.slmtecnologia.config.security.user.Permission.PERSON_READ;
import static com.slmtecnologia.config.security.user.Permission.PERSON_UPDATE;
@RequiredArgsConstructor
public enum Role {

    USER(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_DELETE,
                    USER_CREATE
                    )
    ),
    ADMIN(
            Set.of(
                    PERSON_READ,
                    PERSON_UPDATE,
                    PERSON_DELETE,
                    PERSON_CREATE
            )
    ),
    PERSON(
            Set.of(
                    PERSON_READ,
                    PERSON_UPDATE,
                    PERSON_DELETE,
                    PERSON_CREATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
