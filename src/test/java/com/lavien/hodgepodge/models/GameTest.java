package com.lavien.hodgepodge.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

  private Game test;

  @Before
  public void setUp() throws Exception {
    test = new Game("stay");
  }

  @Test
  public void Game_IdNull_True() {
    assertNull(test.getId());
  }

  @Test(expected = NullPointerException.class)
  public void Game_IdNotNull_NullPointerException() {
    assertEquals(1, (long) test.getId());
  }

  @Test
  public void Game_ListsSizeZero_True() {
    assertEquals(test.getGameCode(), "stay");
    assertEquals(test.getAlchemists().size(), 0);
    assertEquals(test.getAvailableMerchants().size(), 0);
    assertEquals(test.getUnavailableMerchants().size(), 0);
    assertEquals(0, test.getAvailableMixtures().size());
    assertEquals(0, test.getUnavailableMixtures().size());
  }

  @Test
  public void Game_ListsSizeOne_False() {
    assertNotEquals(test.getGameCode(), "doNotStay");
    assertNotEquals(test.getAlchemists().size(), 1);
    assertNotEquals(test.getAvailableMerchants().size(), 1);
    assertNotEquals(test.getUnavailableMerchants().size(), 1);
    assertNotEquals(1, test.getAvailableMixtures().size());
    assertNotEquals(1, test.getUnavailableMixtures().size());
  }
}
