package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.exceptions.GameIsAlreadyExistException;
import com.lavien.hodgepodge.exceptions.GameNotFoundException;
import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;

import com.lavien.hodgepodge.repositories.AlchemistRepository;
import com.lavien.hodgepodge.repositories.GameRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lavien.hodgepodge.models.Mixture;
import com.lavien.hodgepodge.models.merchants.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

  private final MixtureService mixtureService;
  private final MerchantService merchantService;
  private final GameRepository gameRepository;
  private final AlchemistRepository alchemistRepository;

  @Autowired
  public GameServiceImpl(MixtureService mixtureService, MerchantService merchantService,
      GameRepository gameRepository, AlchemistRepository alchemistRepository) {
    this.mixtureService = mixtureService;
    this.merchantService = merchantService;
    this.gameRepository = gameRepository;
    this.alchemistRepository = alchemistRepository;
  }

  @Override
  public Game setUp(List<Alchemist> alchemists, String gameCode) {
    Game game = getGameByGameCode(gameCode);
    alchemists.forEach(alchemistRepository::save);
    alchemists.forEach(game::addAlchemist);

    //1. Alchemistek sorrendje random beállítva
    game.setAlchemists(randomizeAlchemist(alchemists));
    //2. Alchemistek sorrendje alapján kezdő ingredientek
    setUpStarterIngredients(game.getAlchemists());
    //3. Starter mixture-ök
    setStarterMixtures(game.getUnavailableMixtures(), game.getAvailableMixtures());
    //4. Coin
    setCoins(game.getAvailableMixtures(), game.getAlchemists().size());
    //5. Starter Merchants
    setStarterMerchants(game.getUnavailableMerchants(), game.getAvailableMerchants());
    //6. Kezdokartyak
    setStarterHands(game.getAlchemists());
    // 7. Beallitasok mentese
    gameRepository.save(game);
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

  private List<Alchemist> setUpStarterIngredients(List<Alchemist> alchemists) {
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
    return alchemists;
  }

  private void setStarterMixtures(List<Mixture> unavailableMixtures, List<Mixture> availableMixtures) {
    setStarterUnavailablefMixtures(unavailableMixtures);
    setStarterAvailableMixtures(unavailableMixtures, availableMixtures);
  }

  private List<Mixture> setStarterUnavailablefMixtures(List<Mixture> unavailableMixtures) {
    for (Mixture mixture : mixtureService.findAll()) {
      unavailableMixtures.add(mixture);
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

  private List<Mixture> setCoins(List<Mixture> availableMixtures, int numberOfPlayers) {
    availableMixtures.get(0).setGoldCoin(2 * numberOfPlayers);
    availableMixtures.get(1).setSilverCoin(2 * numberOfPlayers);
    return availableMixtures;
  }

  private void setStarterMerchants(List<Merchant> unavailableMerchants, List<Merchant> availableMerchants) {
    setUnavailableMerchants(unavailableMerchants);
    setAvailableMerchants(unavailableMerchants, availableMerchants);
  }

  private List<Merchant> setUnavailableMerchants(List<Merchant> unavailableMerchant) {
    for (Merchant merchant : merchantService.findStarterUnavailableMerchants()) {
      unavailableMerchant.add(merchant);
    }
    return unavailableMerchant;
  }

  private List<Merchant> setAvailableMerchants(List<Merchant> unavailableMerchants, List<Merchant> availableMerchants) {
    Random random = new Random();
    for (int i = 0; i < 6; i++) {
      int randomIndex = random.nextInt(unavailableMerchants.size());
      availableMerchants.add(unavailableMerchants.get(randomIndex));
      unavailableMerchants.remove(randomIndex);
      merchantService.save(availableMerchants.get(i));
    }
    return availableMerchants;
  }

  private List<Alchemist> setStarterHands(List<Alchemist> alchemists) {
    List<Merchant> starterCards = merchantService.pickUpStarterCards();

    alchemists.get(0).setMerchantsInHand(starterCards);
    alchemists.get(1).setMerchantsInHand(starterCards);

    if (alchemists.size() >= 3) {
      alchemists.get(2).setMerchantsInHand(starterCards);
      if (alchemists.size() >= 4) {
        alchemists.get(3).setMerchantsInHand(starterCards);
        if (alchemists.size() == 5) {
          alchemists.get(4).setMerchantsInHand(starterCards);
        }
      }
    }
    return alchemists;
  }

  @Override
  public List<Game> getAll() {
    return this.gameRepository.findAll();
  }

  @Override
  public Game getGameByGameCode(String gameCode) {
    return this.gameRepository.getGameByGameCode(gameCode).orElseThrow(GameNotFoundException::new);
  }

  @Override
  public Game create(Game newGame) throws GameIsAlreadyExistException {
    if (!this.gameRepository.getGameByGameCode(newGame.getGameCode()).isPresent()) {
      this.gameRepository.save(newGame);
    } else throw new GameIsAlreadyExistException();
    return getGameByGameCode(newGame.getGameCode());
  }

}
