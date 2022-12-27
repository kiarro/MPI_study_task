package com.mpi.alienresearch.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    RESEARCHER,
    ADMIN,
    DIRECTOR,
    ANALYTIC,
    TECHNICIAN,
    LANDER;

    @Override
    public String getAuthority() {
        return name();
    }
}

