package com.martikan.quizapi.util;

import com.martikan.quizapi.domain.user.SimpleUser;
import com.martikan.quizapi.domain.user.User;

/**
 * Build Projection interfaces for related entities.
 */
public class ProjectionBuilderUtil {

    /**
     * Build {@link SimpleUser} from {@link User} entity.
     * @param user {@link User}
     * @return {@link SimpleUser}
     */
    public static SimpleUser buildSimpleUser(final User user) {
        return new SimpleUser() {
            @Override
            public Long getId() {
                return user.getId();
            }

            @Override
            public String getEmail() {
                return user.getEmail();
            }

            @Override
            public String getFirstName() {
                return user.getFirstName();
            }

            @Override
            public String getLastName() {
                return user.getLastName();
            }
        };
    }

}
