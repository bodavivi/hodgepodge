package com.lavien.hodgepodge.controllers;

import java.util.List;
import com.lavien.hodgepodge.models.Game;
import com.lavien.hodgepodge.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
