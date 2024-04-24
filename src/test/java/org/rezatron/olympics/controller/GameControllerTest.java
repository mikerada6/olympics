package org.rezatron.olympics.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.Game;
import org.rezatron.olympics.service.GameService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    void testGetAllGames() {
        // Arrange
        Game game1 = new Game();
        game1.setId(UUID.randomUUID());
        game1.setName("Game 1");

        Game game2 = new Game();
        game2.setId(UUID.randomUUID());
        game2.setName("Game 2");

        List<Game> games = Arrays.asList(game1, game2);
        when(gameService.getAllGames()).thenReturn(games);

        // Act
        List<Game> result = gameController.getAllGames().getBody();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Game 1", result.get(0).getName());
        assertEquals("Game 2", result.get(1).getName());
    }

    @Test
    void testGetGameById() {
        // Arrange
        UUID gameId = UUID.randomUUID();
        Game game = new Game();
        game.setId(gameId);
        game.setName("Test Game");
        when(gameService.getGameById(gameId)).thenReturn(Optional.of(game));

        // Act
        Game result = gameController.getGameById(gameId).getBody();

        // Assert
        assertEquals("Test Game", result.getName());
    }

    @Test
    void testCreateGame() {
        // Arrange
        Game game = new Game();
        game.setName("New Game");
        when(gameService.createGame(any())).thenReturn(game);

        // Act
        Game result = gameController.createGame(game).getBody();

        // Assert
        assertEquals("New Game", result.getName());
    }

    @Test
    void testUpdateGame() {
        // Arrange
        UUID gameId = UUID.randomUUID();
        Game updatedGame = new Game();
        updatedGame.setId(gameId);
        updatedGame.setName("Updated Game");
        when(gameService.updateGame(eq(gameId), any())).thenReturn(updatedGame);

        // Act
        Game result = gameController.updateGame(gameId, updatedGame).getBody();

        // Assert
        assertEquals("Updated Game", result.getName());
    }

    @Test
    void testDeleteGame() {
        // Arrange
        UUID gameId = UUID.randomUUID();

        // Act
        gameController.deleteGame(gameId);

        // Assert
        verify(gameService, times(1)).deleteGame(gameId);
    }
}
