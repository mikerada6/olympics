package org.rezatron.olympics.controller;

import org.rezatron.olympics.domain.Game;
import org.rezatron.olympics.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable UUID id) {
        return gameService.getGameById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game gameRequest) {
        Game createdGame = gameService.createGame(gameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable UUID id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }


}