package com.martikan.quizapi.repository;

import com.martikan.quizapi.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Repository for {@link User} entity.
 */
@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query("from User u " +
            "left join fetch u.roles r " +
            "where u.email = :email")
    Optional<User> findUserByEmail(String email);

    boolean existsUserByEmail(String email);

}
