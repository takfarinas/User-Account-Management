package com.technical.test.controller;

import com.technical.test.aspect.Loggable;
import com.technical.test.mapper.UserMapper;
import com.technical.test.resource.UserIdResource;
import com.technical.test.resource.UserResource;
import com.technical.test.service.domain.User;
import com.technical.test.service.service.UserService;
import com.technical.test.validator.ValidUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "Create a User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User creation is performed Successfully"),
            @ApiResponse(code = 400, message = "Something doesn't match with requirement"),
    })
    @Loggable
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserIdResource create(@RequestBody  @ValidUser final UserResource userResource) {
        User user = userService.create(userMapper.asUser(userResource));
        return userMapper.asUserIdResource(user);
    }

    @ApiOperation(value = "Find a specific User by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A user is found Successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Loggable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserIdResource get(@PathVariable final String id) {
        return userMapper.asUserIdResource(userService.get(id));
    }
}
