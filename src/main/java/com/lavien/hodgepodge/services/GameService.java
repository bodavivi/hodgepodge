package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;
import java.util.List;

public interface GameService {

  Game setUp(List<Alchemist> alchemists);

  List<Game> getAll();

  Game getGameByGameCode(String gameCode);

  void create(Game newGame);

  void deleteGameById(Long id);

  void update(Long id, Game game);
}
