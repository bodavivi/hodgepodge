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
  private final Random random;

  @Autowired
  public GameServiceImpl(MixtureService mixtureService, MerchantService merchantService,
      GameRepository gameRepository, AlchemistRepository alchemistRepository) {
    this.mixtureService = mixtureService;
    this.merchantService = merchantService;
    this.gameRepository = gameRepository;
    this.alchemistRepository = alchemistRepository;
    this.random = new Random();
  }

  @Override
  public Game setUp(List<Alchemist> alchemists, String gameCode) {
    Game game = getGameByGameCode(gameCode);
    alchemists.forEach(this.alchemistRepository::save);
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
    this.gameRepository.save(game);
    return game;
  }

  public List<Alchemist> randomizeAlchemist(List<Alchemist> alchemists) {
    List<Alchemist> alchemistsRandomOrder = new ArrayList<>();
    for (int i = 0; i < alchemists.size(); i++) {
      int nextAlchemist = this.random.nextInt(alchemists.size());
      alchemistsRandomOrder.add(alchemists.get(nextAlchemist));
      alchemists.remove(nextAlchemist);
      i--;
    }
    return alchemistsRandomOrder;
  }

  public List<Alchemist> setUpStarterIngredients(List<Alchemist> alchemists) {
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

  public void setStarterMixtures(List<Mixture> unavailableMixtures, List<Mixture> availableMixtures) {
    setStarterUnavailablefMixtures(unavailableMixtures);
    setStarterAvailableMixtures(unavailableMixtures, availableMixtures);
  }

  public List<Mixture> setStarterUnavailablefMixtures(List<Mixture> unavailableMixtures) {
    for (Mixture mixture : this.mixtureService.findAll()) {
      unavailableMixtures.add(mixture);
    }
    return unavailableMixtures;
  }

  public List<Mixture> setStarterAvailableMixtures(List<Mixture> unavailableMixtures, List<Mixture> availableMixtures) {
    for (int i = 0; i < 5; i++) {
      int randomIndex = this.random.nextInt(unavailableMixtures.size());
      availableMixtures.add(unavailableMixtures.get(randomIndex));
      unavailableMixtures.remove(unavailableMixtures.get(randomIndex));
      this.mixtureService.save(availableMixtures.get(i));
    }
    return availableMixtures;
  }

  public List<Mixture> setCoins(List<Mixture> availableMixtures, int numberOfPlayers) {
    availableMixtures.get(0).setGoldCoin(2 * numberOfPlayers);
    availableMixtures.get(1).setSilverCoin(2 * numberOfPlayers);
    return availableMixtures;
  }

  public void setStarterMerchants(List<Merchant> unavailableMerchants, List<Merchant> availableMerchants) {
    setUnavailableMerchants(unavailableMerchants);
    setStarterAvailableMerchants(unavailableMerchants, availableMerchants);
  }

  public List<Merchant> setUnavailableMerchants(List<Merchant> unavailableMerchant) {
    for (Merchant merchant : this.merchantService.findStarterUnavailableMerchants()) {
      unavailableMerchant.add(merchant);
    }
    return unavailableMerchant;
  }

  public List<Merchant> setStarterAvailableMerchants(List<Merchant> unavailableMerchants, List<Merchant> availableMerchants) {
    for (int i = 0; i < 6; i++) {
      int randomIndex = this.random.nextInt(unavailableMerchants.size());
      availableMerchants.add(unavailableMerchants.get(randomIndex));
      unavailableMerchants.remove(randomIndex);
      this.merchantService.save(availableMerchants.get(i));
    }
    return availableMerchants;
  }

  public List<Alchemist> setStarterHands(List<Alchemist> alchemists) {
    List<Merchant> starterCards = this.merchantService.pickUpStarterCards();

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
    return this.gameRepository.getGameByGameCode(newGame.getGameCode()).orElseThrow(GameNotFoundException::new);
  }

  @Override
  public void deleteById(Long id) {
    this.gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
    this.gameRepository.deleteById(id);
  }

  @Override
  public Game update(Long id, Game game) {
    Game gameToUpdate = this.gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
    gameToUpdate.setGameCode(game.getGameCode());
    this.gameRepository.save(gameToUpdate);
    return this.gameRepository.getGameByGameCode(game.getGameCode()).orElseThrow(GameNotFoundException::new);
  }

}
