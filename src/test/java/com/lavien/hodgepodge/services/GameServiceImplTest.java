package com.lavien.hodgepodge.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lavien.hodgepodge.exceptions.GameIsAlreadyExistException;
import com.lavien.hodgepodge.exceptions.GameNotFoundException;
import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Game;
import com.lavien.hodgepodge.models.Mixture;
import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.models.merchants.ObtainIngrMerchant;
import com.lavien.hodgepodge.models.merchants.TradeIngrMerchant;
import com.lavien.hodgepodge.models.merchants.UpdateIngrMerchant;
import com.lavien.hodgepodge.repositories.AlchemistRepository;
import com.lavien.hodgepodge.repositories.GameRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@MockBeans({@MockBean(GameRepository.class), @MockBean(MixtureService.class),
    @MockBean(MerchantService.class), @MockBean(AlchemistRepository.class)})
class GameServiceImplTest {

  private GameServiceImpl gameService;
  private GameRepository gameRepository;
  private MixtureService mixtureService;
  private MerchantService merchantService;
  private AlchemistRepository alchemistRepository;
  private List<Alchemist> alchemists;
  public static final String GAMECODE = "testCode";
  public List<Mixture> mixtures;
  public List<Merchant> merchants;
  public Random randomNumberMock;

  @BeforeEach
  public void setUpForTests() {
    this.gameRepository = mock(GameRepository.class);
    this.mixtureService = mock(MixtureService.class);
    this.merchantService = mock(MerchantService.class);
    this.alchemistRepository = mock(AlchemistRepository.class);
    this.randomNumberMock = mock(Random.class);

    this.gameService = new GameServiceImpl(
        this.mixtureService, this.merchantService,
        this.gameRepository, this.alchemistRepository,
        this.randomNumberMock);

    this.alchemists = new ArrayList<>(Arrays.asList(new Alchemist(), new Alchemist()));
    this.mixtures = new ArrayList<>(Arrays.asList(
        new Mixture(10, 1, 1, 1, 1),
        new Mixture(11, 2, 2, 2, 2)));
    this.merchants = new ArrayList<>(Arrays.asList(
        new ObtainIngrMerchant(1, 1, 1, 1),
        new TradeIngrMerchant(0, 0, 0, 0, 0, 0, 0, 100),
        new UpdateIngrMerchant(3)));
  }

  public void addMoreMixtures() {
    this.mixtures.addAll(new ArrayList<>(Arrays.asList(
        new Mixture(12, 1, 2, 3, 4),
        new Mixture(13, 1, 2, 3, 4),
        new Mixture(14, 1, 2, 3, 4),
        new Mixture(15, 1, 2, 3, 4))));
  }

  public void addMoreMerchants() {
    this.merchants.addAll(new ArrayList<>(Arrays.asList(
        new UpdateIngrMerchant(4),
        new UpdateIngrMerchant(5),
        new UpdateIngrMerchant(6),
        new UpdateIngrMerchant(7))));
  }

  @Test
  public void setUp_CallCorrespondingMethodsToSetUpGame_ThereIsAGameWith2Alchemists() {
    Game test = new Game(GAMECODE);
    test.setId(1L);
    Game[] resultGame = new Game[1];
    Alchemist[] alchemistToSave = new Alchemist[1];

    when(this.gameRepository.getGameByGameCode(GAMECODE)).thenReturn(Optional.of(test));

    when(this.alchemistRepository.save(any()))
        .then(invocation -> {
          alchemistToSave[0] = (Alchemist) invocation.getArguments()[0];
          return alchemistToSave[0];
        });

    // randomizeAlchemist, setStarterAvailableMixtures, setStarterAvailableMerchants
    when(this.randomNumberMock.nextInt(anyInt())).thenReturn(0);

    // setStarterUnavailableMixtures
    addMoreMixtures();
    when(this.mixtureService.findAll()).thenReturn(this.mixtures);

    // setStarterUnavailableMerchants
    addMoreMerchants();
    when(this.merchantService.findStarterUnavailableMerchants()).thenReturn(this.merchants);

    // setStarterHands
    when(this.merchantService.pickUpStarterCards()).thenReturn(new ArrayList<>(Arrays.asList(
        new ObtainIngrMerchant(2, 0, 0, 0),
        new UpdateIngrMerchant(2))));

    when(this.gameRepository.save(any()))
        .then(invocationOnMock -> {
          resultGame[0] = (Game) invocationOnMock.getArguments()[0];
          return resultGame[0];
        });

    Game result = this.gameService.setUp(this.alchemists, GAMECODE);

    assertNotNull(result);
    assertEquals(GAMECODE, result.getGameCode());
    assertEquals(1, result.getId());
    assertEquals(2, result.getAlchemists().size());
    assertEquals(5, result.getAvailableMixtures().size());
    assertEquals(6, result.getAvailableMerchants().size());

    verify(this.gameRepository).getGameByGameCode(GAMECODE);
    verify(this.alchemistRepository, times(2)).save(any());
    verify(this.randomNumberMock, times(13)).nextInt(anyInt());
    verify(this.mixtureService).findAll();
    verify(this.merchantService).findStarterUnavailableMerchants();
    verify(this.merchantService).pickUpStarterCards();
    verify(this.gameRepository).save(any());
  }

  @Test
  public void randomizeAlchemist_ReturnRandomOrder_ListWith2Alchemists() {
    this.alchemists.get(0).setId(1L);

    when(this.randomNumberMock.nextInt(anyInt())).thenReturn(0);

    List<Alchemist> result = this.gameService.randomizeAlchemist(this.alchemists);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).getId());

    verify(this.randomNumberMock, times(2)).nextInt(anyInt());
  }

  @Test
  public void randomizeAlchemist_ReturnRandomOrder_ListWith5Alchemists() {
    this.alchemists.addAll(new ArrayList<>(Arrays.asList(new Alchemist(), new Alchemist(), new Alchemist())));
    this.alchemists.get(0).setId(1L);
    this.alchemists.get(4).setId(5L);

    when(this.randomNumberMock.nextInt(anyInt())).thenReturn(0);

    List<Alchemist> result = this.gameService.randomizeAlchemist(this.alchemists);

    assertNotNull(result);
    assertEquals(5, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals(5, result.get(4).getId());

    verify(this.randomNumberMock, times(5)).nextInt(anyInt());
  }

  @Test
  public void setUpStarterIngredients_ReturnListOfAlchemistsWithStarterIngredients_ListWith2Alchemists() {
    List<Alchemist> result = this.gameService.setUpStarterIngredients(this.alchemists);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(3, this.alchemists.get(0).getIngrRoot());
    assertEquals(4, this.alchemists.get(1).getIngrRoot());
  }

  @Test
  public void setUpStarterIngredients_ReturnListOfAlchemistsWithStarterIngredients_ListWith5Alchemists() {
    this.alchemists.addAll(new ArrayList<>(Arrays.asList(new Alchemist(), new Alchemist(), new Alchemist())));
    List<Alchemist> result = this.gameService.setUpStarterIngredients(this.alchemists);

    assertNotNull(result);
    assertEquals(5, result.size());
    assertEquals(3, this.alchemists.get(0).getIngrRoot());
    assertEquals(4, this.alchemists.get(1).getIngrRoot());
    assertEquals(4, this.alchemists.get(2).getIngrRoot());
    assertEquals(3, this.alchemists.get(3).getIngrRoot());
    assertEquals(1, this.alchemists.get(3).getIngrMushroom());
    assertEquals(3, this.alchemists.get(4).getIngrRoot());
    assertEquals(1, this.alchemists.get(4).getIngrMushroom());
  }

  @Test
  public void setStarterMixtures_CallCorrespondingMethods_ValidArguments() {
    GameServiceImpl gameServiceMock = mock(GameServiceImpl.class);

    doNothing().when(gameServiceMock).setStarterMixtures(isA(List.class), isA(List.class));
    gameServiceMock.setStarterMixtures(new ArrayList<>(), new ArrayList<>());

    verify(gameServiceMock).setStarterMixtures(new ArrayList<>(), new ArrayList<>());
  }


  @Test
  public void setStarterUnavailableMixtures_ReturnAllMixtures_ThereAre2Mixtures() {
    when(this.mixtureService.findAll()).thenReturn(this.mixtures);

    List<Mixture> result = this.gameService.setStarterUnavailablefMixtures(new ArrayList<>());

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(10, result.get(0).getPoint());
    assertEquals(11, result.get(1).getPoint());

    verify(this.mixtureService).findAll();
  }

  @Test
  public void setStarterAvailableMixtures_Return5RandomMixtures_ThereAre6Mixtures() {
    addMoreMixtures();

    when(this.randomNumberMock.nextInt(anyInt())).thenReturn(0);

    List<Mixture> result = this.gameService.setStarterAvailableMixtures(this.mixtures, new ArrayList<>());

    assertNotNull(result);
    assertEquals(5, result.size());
    assertEquals(10, result.get(0).getPoint());
    assertEquals(11, result.get(1).getPoint());
    assertEquals(12, result.get(2).getPoint());
    assertEquals(13, result.get(3).getPoint());
    assertEquals(14, result.get(4).getPoint());

    verify(this.randomNumberMock, times(5)).nextInt(anyInt());
  }

  @Test
  public void setCoins_SetGoldAndSilverCoins_ThereAre2Alchemists() {
    List<Mixture> result = this.gameService.setCoins(this.mixtures, this.alchemists.size());

    assertNotNull(result);
    assertEquals(4, result.get(0).getGoldCoin());
    assertEquals(4, result.get(1).getSilverCoin());
  }

  @Test
  public void setStarterMerchants_CallCorrespondingMethods_ValidArguments() {
    GameServiceImpl gameServiceMock = mock(GameServiceImpl.class);

    doNothing().when(gameServiceMock).setStarterMerchants(isA(List.class), isA(List.class));
    gameServiceMock.setStarterMerchants(new ArrayList<>(), new ArrayList<>());

    verify(gameServiceMock, times(1)).setStarterMerchants(new ArrayList<>(), new ArrayList<>());
  }

  @Test
  public void setStarterUnavailableMerchants_ReturnAllMerchants_ThereAre3Merchants() {
    when(this.merchantService.findStarterUnavailableMerchants()).thenReturn(this.merchants);

    List<Merchant> result = this.gameService.setStarterUnavailableMerchants(new ArrayList<>());

    assertNotNull(result);
    assertEquals(3, result.size());

    verify(this.merchantService).findStarterUnavailableMerchants();
  }

  @Test
  public void setStarterAvailableMerchants_Return6RandomMerchants_ThereAre7Merchants() {
    addMoreMerchants();

    when(this.randomNumberMock.nextInt(anyInt())).thenReturn(0);

    List<Merchant> result = this.gameService.setStarterAvailableMerchants(this.merchants, new ArrayList<>());

    assertNotNull(result);
    assertEquals(6, result.size());

    verify(this.randomNumberMock, times(6)).nextInt(anyInt());
  }

  @Test
  public void setStarterHands_SetStarterMerchantCardsAndReturnAlchemists_ThereAre2Alchemists() {
    this.merchants.remove(1);
    when(this.merchantService.pickUpStarterCards()).thenReturn(this.merchants);

    List<Alchemist> result = this.gameService.setStarterHands(this.alchemists);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(2, result.get(0).getMerchantsInHand().size());
    assertEquals(2, result.get(1).getMerchantsInHand().size());

    verify(this.merchantService).pickUpStarterCards();
  }

  @Test
  public void setStarterHands_SetStarterMerchantCardsAndReturnAlchemists_ThereAre5Alchemists() {
    this.alchemists.addAll(new ArrayList<>(Arrays.asList(new Alchemist(), new Alchemist(), new Alchemist())));

    this.merchants.remove(1);
    when(this.merchantService.pickUpStarterCards()).thenReturn(this.merchants);

    List<Alchemist> result = this.gameService.setStarterHands(this.alchemists);

    assertNotNull(result);
    assertEquals(5, result.size());
    assertEquals(2, result.get(0).getMerchantsInHand().size());
    assertEquals(2, result.get(1).getMerchantsInHand().size());
    assertEquals(2, result.get(2).getMerchantsInHand().size());
    assertEquals(2, result.get(3).getMerchantsInHand().size());
    assertEquals(2, result.get(4).getMerchantsInHand().size());

    verify(this.merchantService).pickUpStarterCards();
  }

  @Test
  public void getAll_ReturnAllGames_TwoGames() {
    Game test1 = new Game(GAMECODE + "1");
    Game test2 = new Game(GAMECODE + "2");
    List<Game> games = new ArrayList<>(Arrays.asList(test1, test2));

    when(this.gameRepository.findAll()).thenReturn(games);

    List<Game> result = this.gameService.getAll();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(games, this.gameService.getAll());

    verify(this.gameRepository, times(2)).findAll();
  }

  @Test
  public void getAll_ReturnEmptyList_NoGame() {
    when(this.gameRepository.findAll()).thenReturn(new ArrayList<>());
    assertEquals(new ArrayList<>(), this.gameService.getAll());
    verify(this.gameRepository).findAll();
  }

  @Test
  public void getGameByGameCode_ReturnGame_GameWithExistingGameCode() {
    Game test = new Game(GAMECODE);

    when(this.gameRepository.getGameByGameCode(GAMECODE)).thenReturn(Optional.of(test));

    Game result = this.gameService.getGameByGameCode(GAMECODE);

    assertNotNull(result);
    assertEquals(GAMECODE, result.getGameCode());
    assertEquals(test, this.gameService.getGameByGameCode(GAMECODE));

    verify(this.gameRepository, times(2)).getGameByGameCode(GAMECODE);
  }

  @Test
  public void getGameByGameCode_ThrowGameNotFoundException_GameWithNonExistingGameCode() {
    when(this.gameRepository.getGameByGameCode("notExist")).thenReturn(Optional.empty());
    assertThrows(GameNotFoundException.class, () -> this.gameService.getGameByGameCode("notExist"));
  }

  @Test
  public void create_CreateNewGame_GameWithNonExistingGameCode() {
    Game expectedGame = new Game(GAMECODE);
    expectedGame.setId(1L);
    Game[] resultGame = new Game[1];

    when(this.gameRepository.save(any()))
        .then(invocationOnMock -> {
          Game game = (Game) invocationOnMock.getArguments()[0];
          game.setId(1L);
          resultGame[0] = game;
          return game;
        });

    when(this.gameRepository.getGameByGameCode(GAMECODE))
        .thenReturn(Optional.empty())
        .thenReturn(Optional.of(expectedGame));

    this.gameService.create(new Game(GAMECODE));

    assertNotNull(resultGame);
    assertEquals(expectedGame.getId(), resultGame[0].getId());
    assertEquals(expectedGame.getGameCode(), resultGame[0].getGameCode());
    assertEquals(expectedGame.getAlchemists(), resultGame[0].getAlchemists());

    verify(this.gameRepository).save(any());
    verify(this.gameRepository, times(2)).getGameByGameCode(GAMECODE);
  }

  @Test
  public void create_ThrowGameIsAlreadyExistException_GameWithExistingGameCode() {
    Game test = new Game(GAMECODE);

    when(this.gameRepository.getGameByGameCode(GAMECODE)).thenReturn(Optional.of(test));

    assertThrows(GameIsAlreadyExistException.class, () -> this.gameService.create(new Game(GAMECODE)));
    verify(this.gameRepository).getGameByGameCode(GAMECODE);
  }

  @Test
  public void deleteById_SoftDeleteGame_ValidId() {
    Game test = new Game(GAMECODE);
    test.setId(1L);

    when(this.gameRepository.findById(1L)).thenReturn(Optional.of(test));
    doNothing().when(this.gameRepository).deleteById(1L);

    this.gameService.deleteById(1L);

    verify(this.gameRepository).deleteById(1L);
  }

  @Test
  public void deleteById_ThrowGameNotFoundException_InvalidId() {
    when(this.gameRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(GameNotFoundException.class, () -> this.gameService.deleteById(1L));
    verify(this.gameRepository).findById(1L);
  }

  @Test
  public void update_UpdateGameCodeCorrectly_GameWithExistingGameCode() {
    Game game = new Game(GAMECODE);
    game.setId(1L);
    Game[] resultGame = new Game[1];

    when(this.gameRepository.findById(1L)).thenReturn(Optional.of(game));
    when(this.gameRepository.getGameByGameCode(GAMECODE)).thenReturn(Optional.of(game));
    when(this.gameRepository.save(any()))
        .then(invocationOnMock -> {
          resultGame[0] = (Game) invocationOnMock.getArguments()[0];
          return resultGame[0];
        });

    this.gameService.update(1L, game);

    assertNotNull(resultGame[0]);
    assertEquals(game.getGameCode(), resultGame[0].getGameCode());

    verify(this.gameRepository).save(any());
    verify(this.gameRepository).findById(1L);
    verify(this.gameRepository).getGameByGameCode(GAMECODE);
  }

  @Test
  public void update_ThrowGameNotFoundException_GameWithNonExistingGameCode() {
    when(this.gameRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(GameNotFoundException.class, () -> this.gameService.update(1L, new Game(GAMECODE)));
    verify(this.gameRepository).findById(1L);
  }

}
