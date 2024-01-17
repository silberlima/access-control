package com.slmtecnologia.model.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {

    USER(
            Set.of(
                    Permission.USER_READ,
                    Permission.USER_UPDATE,
                    Permission.USER_DELETE,
                    Permission.USER_CREATE
                    )
    ),
    ADMIN(
            Set.of(
                    Permission.PERSON_READ,
                    Permission.PERSON_UPDATE,
                    Permission.PERSON_DELETE,
                    Permission.PERSON_CREATE,
                    Permission.USER_READ,
                    Permission.USER_UPDATE,
                    Permission.USER_DELETE,
                    Permission.USER_CREATE,
                    Permission.PERMISSION_READ,
                    Permission.PERMISSION_UPDATE,
                    Permission.PERMISSION_DELETE,
                    Permission.PERMISSION_CREATE,
                    Permission.ROLE_READ,
                    Permission.ROLE_UPDATE,
                    Permission.ROLE_DELETE,
                    Permission.ROLE_CREATE,
                    Permission.APPLICATION_READ,
                    Permission.APPLICATION_UPDATE,
                    Permission.APPLICATION_DELETE,
                    Permission.APPLICATION_CREATE
            )
    ),
    PERSON(
            Set.of(
                    Permission.PERSON_READ,
                    Permission.PERSON_UPDATE,
                    Permission.PERSON_DELETE,
                    Permission.PERSON_CREATE
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
