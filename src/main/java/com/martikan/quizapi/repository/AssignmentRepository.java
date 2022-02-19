package com.martikan.quizapi.repository;

import com.martikan.quizapi.aspect.IsUser;
import com.martikan.quizapi.domain.assignment.Assignment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for {@link Assignment} entity.
 */
@IsUser
@RepositoryRestResource
public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long> {

}
