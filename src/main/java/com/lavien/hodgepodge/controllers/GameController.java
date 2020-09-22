package com.lavien.hodgepodge.controllers;

import java.util.List;
import com.lavien.hodgepodge.models.Game;
import com.lavien.hodgepodge.services.GameService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "http://localhost:8080" })
@RestController
public class GameController {

  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping(value = "/games")
  public ResponseEntity<List<Game>> getGames() {
    return ResponseEntity.ok(this.gameService.getAll());
  }

  @GetMapping(value = "/games/{gameCode}")
  public ResponseEntity<Game> getGameByGameCode(@PathVariable String gameCode) {
    return ResponseEntity.ok(this.gameService.getGameByGameCode(gameCode));
  }

  @PostMapping(value = "/games")
  public ResponseEntity<Game> createGame(@RequestBody @Valid Game game) {
    this.gameService.create(game);
    return getGameByGameCode(game.getGameCode());
  }

  @DeleteMapping(value = "/games/{id}")
  public ResponseEntity<?> deleteGameById(@PathVariable Long id) {
    this.gameService.deleteGameById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping(value = "/games/{id}")
  public ResponseEntity<HttpStatus> updateGame(@PathVariable Long id, @RequestBody Game game) {
    this.gameService.update(id, game);
    return ResponseEntity.ok().build();
  }

}
