package com.lavien.hodgepodge.controllers;

import com.lavien.hodgepodge.models.game.dto.GameRequestDTO;
import com.lavien.hodgepodge.models.game.dto.GameResponseDTO;
import java.util.List;
import com.lavien.hodgepodge.models.game.Game;
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
  public ResponseEntity<List<GameResponseDTO>> getGames() {
    return ResponseEntity.ok(this.gameService.getAll());
  }

  @GetMapping(value = "/games/{gameCode}")
  public ResponseEntity<GameResponseDTO> getGameByGameCode(@PathVariable String gameCode) {
    return ResponseEntity.ok(this.gameService.getGameByGameCode(gameCode));
  }

  @PostMapping(value = "/games")
  public ResponseEntity<HttpStatus> createGame(@RequestBody @Valid GameRequestDTO requestDTO) {
    this.gameService.create(requestDTO);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/games/{id}")
  public ResponseEntity<HttpStatus> deleteGameById(@PathVariable Long id) {
    this.gameService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/games/{id}")
  public ResponseEntity<HttpStatus> updateGame(@PathVariable Long id, @RequestBody GameRequestDTO requestDTO) {
    this.gameService.update(id, requestDTO);
    return ResponseEntity.noContent().build();
  }

}
