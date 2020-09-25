package com.lavien.hodgepodge.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lavien.hodgepodge.exceptions.GameNotFoundException;
import com.lavien.hodgepodge.models.Game;
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
  private MixtureService mixtureService;
  private MerchantService merchantService;
  private GameRepository gameRepository;
  private AlchemistRepository alchemistRepository;
  public static final String GAMECODE = "testCode";

  @BeforeEach
  public void setUp() {
    this.mixtureService = mock(MixtureService.class);
    this.merchantService = mock(MerchantService.class);
    this.gameRepository = mock(GameRepository.class);
    this.alchemistRepository = mock(AlchemistRepository.class);
    this.gameService = new GameServiceImpl(mixtureService, merchantService, gameRepository, alchemistRepository);
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
  }

}
