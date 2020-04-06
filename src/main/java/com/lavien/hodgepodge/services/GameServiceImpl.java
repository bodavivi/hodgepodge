package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

  @Override
  public Game setUp(List<Alchemist> alchemists) {
    Game game = new Game();
    game.setAlchemists(randomizeAlchemist(alchemists));
    return game;
  }

  private ArrayList<Alchemist> randomizeAlchemist(List<Alchemist> alchemists) {
    ArrayList<Alchemist> alchemistsRandomOrder = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < alchemists.size(); i++) {
      int nextAlchemist = random.nextInt(alchemists.size());
      alchemistsRandomOrder.add(alchemists.get(nextAlchemist));
      alchemists.remove(nextAlchemist);
      i--;
    }
    return alchemistsRandomOrder;
  }
}
