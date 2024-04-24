package org.rezatron.olympics.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rezatron.olympics.domain.Game;
import org.rezatron.olympics.repository.GameRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameService;

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
        when(gameRepository.findAll()).thenReturn(games);

        // Act
        List<Game> result = gameService.getAllGames();

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
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        // Act
        Optional<Game> result = gameService.getGameById(gameId);

        // Assert
        assertEquals("Test Game", result.get().getName());
    }

    @Test
    void testCreateGame() {
        // Arrange
        Game game = new Game();
        game.setName("New Game");
        when(gameRepository.save(any())).thenReturn(game);

        // Act
        Game result = gameService.createGame(game);

        // Assert
        assertEquals("New Game", result.getName());
    }

    @Test
    void testUpdateGame() {
        // Arrange
        UUID gameId = UUID.randomUUID();
        Game existingGame = new Game();
        existingGame.setId(gameId);
        existingGame.setName("Existing Game");

        Game updatedGame = new Game();
        updatedGame.setId(gameId);
        updatedGame.setName("Updated Game");

        when(gameRepository.save(any())).thenReturn(updatedGame);

        // Act
        Game result = gameService.updateGame(gameId, updatedGame);

        // Assert
        assertEquals("Updated Game", result.getName());
    }

    @Test
    void testDeleteGame() {
        // Arrange
        UUID gameId = UUID.randomUUID();

        // Act
        gameService.deleteGame(gameId);

        // Assert
        verify(gameRepository, times(1)).deleteById(gameId);
    }
}