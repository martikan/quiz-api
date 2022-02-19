package com.martikan.quizapi.service;

import com.martikan.quizapi.domain.role.Role;
import com.martikan.quizapi.domain.role.RoleName;

/**
 * Service for {@link com.martikan.quizapi.domain.role.Role} and authentication's stuffs.
 */
public interface RoleService {

    Role findRoleByName(final RoleName name);

}
