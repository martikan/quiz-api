package com.martikan.quizapi.service;

import com.martikan.quizapi.domain.role.Role;
import com.martikan.quizapi.domain.role.RoleName;
import com.martikan.quizapi.exception.ResourceNotFoundException;
import com.martikan.quizapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation for {@link RoleService}.
 */
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * Find role by name.
     * @param name {@link RoleName}
     * @return {@link Role}
     */
    @Override
    public Role findRoleByName(final RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), "name", name));
    }

}
