package com.technical.test.service.service;

import com.technical.test.service.domain.User;
import com.technical.test.service.exception.EntityNotFoundException;
import com.technical.test.service.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(final User user) {
        return this.userRepository.insert(user);
    }

    public User get(final String id) {
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }
}
