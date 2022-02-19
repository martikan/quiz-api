package com.martikan.quizapi.repository;

import com.martikan.quizapi.domain.role.Role;
import com.martikan.quizapi.domain.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Repository for {@link Role} entity.
 */
@RepositoryRestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

}
