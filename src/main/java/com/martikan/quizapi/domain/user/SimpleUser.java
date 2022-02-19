package com.martikan.quizapi.domain.user;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projection for {@link User}.
 * Minimal representation of {@link User} entity.
 */
@Projection(name = "simpleUser", types = User.class)
public interface SimpleUser {

    Long getId();

    String getEmail();

    String getFirstName();

    String getLastName();

}
