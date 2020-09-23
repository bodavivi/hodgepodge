package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.game.Game;
import com.lavien.hodgepodge.models.game.dto.GameRequestDTO;
import com.lavien.hodgepodge.models.game.dto.GameResponseDTO;
import java.util.List;

public interface GameService {

  Game setUp(List<Alchemist> alchemists);

  List<GameResponseDTO> getAll();

  GameResponseDTO getGameByGameCode(String gameCode);

  void create(GameRequestDTO requestDTO);

  void deleteById(Long id);

  void update(Long id, GameRequestDTO requestDTO);
}
