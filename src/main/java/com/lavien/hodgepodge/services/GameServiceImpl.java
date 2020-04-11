package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lavien.hodgepodge.models.Mixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
  private MixtureService mixtureService;

  @Autowired
  public GameServiceImpl(MixtureService mixtureService) {
    this.mixtureService = mixtureService;
  }

  @Override
  public Game setUp(List<Alchemist> alchemists) {
    Game game = new Game();
    //1. Alchemistek sorrendje random beállítva
    game.setAlchemists(randomizeAlchemist(alchemists));
    //2. Alchemistek sorrendje alapján kezdő ingredientek
    setUpStarterIngredients(game.getAlchemists());
    //3.Starter mixture-ök
    setStarterMixtures(game.getUnavailableMixtures(), game.getAvailableMixtures());
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

  private void setUpStarterIngredients(List<Alchemist> alchemists) {

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

  private void setStarterMixtures(List<Mixture> unavailableMixtures, List<Mixture> availableMixtures) {
    setStarterUnavailablefMixtures(unavailableMixtures);
    setStarterAvailableMixtures(unavailableMixtures, availableMixtures);
  }

  private List<Mixture> setStarterUnavailablefMixtures(List<Mixture> unavailableMixtures) {
    for (Mixture mixture : mixtureService.findAll()) {
      unavailableMixtures.add(mixture);
      mixtureService.save(mixture);
    }
    return unavailableMixtures;
  }

  private List<Mixture> setStarterAvailableMixtures(List<Mixture> unavailableMixtures, List<Mixture> availableMixtures) {
    Random random = new Random();
    for (int i = 0; i < 5; i++) {
      int randomIndex = random.nextInt(unavailableMixtures.size());
      availableMixtures.add(unavailableMixtures.get(randomIndex));
      unavailableMixtures.remove(unavailableMixtures.get(randomIndex));
      mixtureService.save(availableMixtures.get(i));
    }
    return availableMixtures;
  }

}
