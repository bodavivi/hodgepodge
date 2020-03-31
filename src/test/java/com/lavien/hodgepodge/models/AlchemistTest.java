package com.lavien.hodgepodge.models;

import static org.junit.Assert.*;

import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.models.merchants.UpdateIngrMerchant;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class AlchemistTest {

  private Alchemist testAlchemist;
  private Game testGame = new Game("TestCode");
  private Mixture testMixture = new Mixture(77, 1, 1, 1, 1);
  private Merchant testMerchant = new UpdateIngrMerchant(1,1,1,1,1,1);

  @Before
  public void setUp() throws Exception {
    testAlchemist = new Alchemist(testGame);
    testAlchemist.setMixtures(new ArrayList<>(Arrays.asList(testMixture)));
    testAlchemist.setMerchantsInHand(new ArrayList<>(Arrays.asList(testMerchant)));
  }

  @Test(expected = NullPointerException.class)
  public void Alchemist_IdNotNull_NullPointerException() {
    assertEquals(1, (long) testAlchemist.getId());
  }

  @Test
  public void Alchemist_CreatedCorrectlyWithGameCode_True() {
    assertEquals(testGame, testAlchemist.getGame());
    assertEquals("TestCode", testAlchemist.getGame().getGameCode());
  }

  @Test
  public void Alchemist_ContainsOneMixture_True() {
    assertEquals(1, testAlchemist.getMixtures().size());
    assertEquals(testMixture, testAlchemist.getMixtures().get(0));
  }

  @Test
  public void Alchemist_ContainsOneMerchantInHand_True() {
    assertEquals(1, testAlchemist.getMerchantsInHand().size());
    assertEquals(testMerchant, testAlchemist.getMerchantsInHand().get(0));
  }
}
