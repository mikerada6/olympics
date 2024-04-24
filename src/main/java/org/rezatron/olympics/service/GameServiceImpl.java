package org.rezatron.olympics.service;

import org.rezatron.olympics.domain.Game;
import org.rezatron.olympics.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameServiceImpl  implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> getGameById(UUID id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(UUID id, Game game) {
        game.setId(id); // Ensure the ID matches the path variable
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(UUID id) {
        gameRepository.deleteById(id);
    }
}