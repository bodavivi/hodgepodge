package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.exceptions.GameIsAlreadyExistException;
import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;
import java.util.List;

public interface GameService {

  Game setUp(List<Alchemist> alchemists, String gameCode);

  List<Game> getAll();

  Game getGameByGameCode(String gameCode);

  Game create(Game newGame) throws GameIsAlreadyExistException;

  void deleteById(Long id);

  Game update(Long id, Game game);
}
