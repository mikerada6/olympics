package org.rezatron.olympics.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.User;
import org.rezatron.olympics.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

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
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        List<User> result = userController.getAllUsers().getBody();

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
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userController.getUserById(userId).getBody();

        // Assert
        assertEquals("User firstName", result.getFirstName());
        assertEquals("User lastName", result.getLastName());
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setFirstName("User firstName");
        user.setLastName("User lastName");
        when(userService.createUser(any())).thenReturn(user);

        // Act
        User result = userController.createUser(user).getBody();

        // Assert
        assertEquals("User firstName", result.getFirstName());
        assertEquals("User lastName", result.getLastName());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Updated User firstName");
        updatedUser.setLastName("Updated User lastName");
        when(userService.updateUser(eq(userId), any())).thenReturn(updatedUser);

        // Act
        User result = userController.updateUser(userId, updatedUser).getBody();

        // Assert
        assertEquals("Updated User firstName", result.getFirstName());
        assertEquals("Updated User lastName", result.getLastName());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        UUID userId = UUID.randomUUID();

        // Act
        userController.deleteUser(userId);

        // Assert
        verify(userService, times(1)).deleteUser(userId);
    }
}