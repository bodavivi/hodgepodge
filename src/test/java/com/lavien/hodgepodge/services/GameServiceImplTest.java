package com.lavien.hodgepodge.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import com.lavien.hodgepodge.repositories.AlchemistRepository;
import com.lavien.hodgepodge.repositories.GameRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
  private List<Alchemist> alchemists;
  public static final String GAMECODE = "testCode";

  @BeforeEach
  public void setUp() {
    this.gameRepository = mock(GameRepository.class);
    this.mixtureService = mock(MixtureService.class);
    this.gameService = new GameServiceImpl(this.mixtureService, mock(MerchantService.class),
        gameRepository, mock(AlchemistRepository.class));
    this.alchemists = new ArrayList<>(Arrays.asList(new Alchemist(), new Alchemist()));
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

  @Test
  public void randomizeAlchemist_ReturnRandomOrder_ListWith2Alchemists() {
    List<Alchemist> result = this.gameService.randomizeAlchemist(this.alchemists);

    assertNotNull(result);
    assertEquals(2, result.size());
  }

  @Test
  public void randomizeAlchemist_ReturnRandomOrder_ListWith5Alchemists() {
    this.alchemists.addAll(new ArrayList<>(Arrays.asList(new Alchemist(), new Alchemist(), new Alchemist())));
    List<Alchemist> result = this.gameService.randomizeAlchemist(this.alchemists);

    assertNotNull(result);
    assertEquals(5, result.size());
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
  public void setStarterUnavailablefMixtures_ReturnAllMixtures_ThereAreTwoMixtures() {
    Mixture test1 = new Mixture(7, 2, 1, 0, 0);
    Mixture test2 = new Mixture(8, 3, 1, 0, 0);
    List<Mixture> mixtures = new ArrayList<>(Arrays.asList(test1, test2));

   when(this.mixtureService.findAll()).thenReturn(mixtures);

   List<Mixture> result = this.gameService.setStarterUnavailablefMixtures(new ArrayList<>());

   assertNotNull(result);
   assertEquals(2, result.size());
   assertEquals(7, result.get(0).getPoint());
   assertEquals(8, result.get(1).getPoint());

   verify(this.mixtureService).findAll();
  }

  @Test
  public void setStarterAvailablefMixtures_ReturnAllMixtures_ThereAreTwoMixtures() {
    fail();
  }

}
