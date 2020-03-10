package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.ingredients.ChickenLeg;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChickenLegTest {
  ChickenLeg test = new ChickenLeg();

  @Test
  public void ChickenLegValue_4_True() {
    assertEquals(test.getValue(), 4);
  }

  @Test
  public void ChickenLegValue_5_False() {
    assertNotEquals(test.getValue(), 5);
  }

  @Test
  public void ChickenLegValue_3_False() {
    assertNotEquals(test.getValue(), 3);
  }

}