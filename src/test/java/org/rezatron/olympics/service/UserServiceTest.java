package org.rezatron.olympics.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.User;
import org.rezatron.olympics.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setFirstName("User 1 firstName");
        user1.setLastName("User 1 lastName");

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setFirstName("User 2 firstName");
        user2.setLastName("User 2 lastName");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("User 1 firstName", result.get(0).getFirstName());
        assertEquals("User 1 lastName", result.get(0).getLastName());

        assertEquals("User 2 firstName", result.get(1).getFirstName());
        assertEquals("User 2 lastName", result.get(1).getLastName());
    }

    @Test
    void testGetUserById() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setFirstName("User firstName");
        user.setLastName("User lastName");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserById(userId);

        // Assert
        assertEquals("User firstName", result.get().getFirstName());
        assertEquals("User lastName", result.get().getLastName());
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setFirstName("User firstName");
        user.setLastName("User lastName");
        when(userRepository.save(any())).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertEquals("User firstName", result.getFirstName());
        assertEquals("User lastName", result.getLastName());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstName("Existing firstName");
        existingUser.setLastName("Existing lastName");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Updated firstName");
        updatedUser.setLastName("Updated lastName");

        when(userRepository.save(any())).thenReturn(updatedUser);

        // Act
        User result = userService.updateUser(userId, updatedUser);

        // Assert
        assertEquals("Updated firstName", result.getFirstName());
        assertEquals("Updated lastName", result.getLastName());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}