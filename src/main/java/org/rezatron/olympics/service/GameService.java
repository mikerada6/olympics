package org.rezatron.olympics.service;

import org.rezatron.olympics.domain.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameService {
    List<Game> getAllGames();
    Optional<Game> getGameById(UUID id);
    Game createGame(Game event);
    Game updateGame(UUID id, Game event);
    void deleteGame(UUID id);
}
