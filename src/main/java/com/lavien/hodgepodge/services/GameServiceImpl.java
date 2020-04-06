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
    setUpStarterIngredients(game);
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

  private void setUpStarterIngredients(Game game) {
    List<Alchemist> alchemists = game.getAlchemists();

    alchemists.get(0).setIngrRoot(3);
    alchemists.get(1).setIngrRoot(4);

    if (alchemists.size() >= 3) {
     alchemists.get(2).setIngrRoot(4);
     if (alchemists.size() >= 4) {
       alchemists.get(3).setIngrRoot(3);
       alchemists.get(3).setIngrMushroom(1);
       if (alchemists.size() == 5) {
         alchemists.get(4).setIngrRoot(3);
         alchemists.get(4).setIngrMushroom(1);
       }
     }
   }
  }
}
