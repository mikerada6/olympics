package org.rezatron.olympics.service;

import org.rezatron.olympics.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(UUID id);

    User createUser(User event);

    User updateUser(UUID id, User event);

    void deleteUser(UUID id);
}
