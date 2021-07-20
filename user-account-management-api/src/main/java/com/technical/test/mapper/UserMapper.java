package com.technical.test.mapper;

import com.technical.test.resource.UserIdResource;
import com.technical.test.resource.UserResource;
import com.technical.test.service.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserIdResource asUserIdResource(User user);
    User asUser(UserResource userResource);
}
