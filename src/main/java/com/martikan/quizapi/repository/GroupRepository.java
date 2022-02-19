package com.martikan.quizapi.repository;

import com.martikan.quizapi.aspect.IsTeacher;
import com.martikan.quizapi.domain.Group;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for {@link Group} entity.
 */
@IsTeacher
@RepositoryRestResource
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
}
