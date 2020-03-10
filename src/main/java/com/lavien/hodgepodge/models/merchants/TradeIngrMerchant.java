package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.ingredients.Ingredient;
import java.util.HashMap;

public class TradeIngrMerchant extends Merchant {

  private HashMap<Ingredient, Integer> from;
  private HashMap<Ingredient, Integer> to;

  public TradeIngrMerchant(
      HashMap<Ingredient, Integer> from,
      HashMap<Ingredient, Integer> to) {
    this.from = from;
    this.to = to;
  }

  public HashMap<Ingredient, Integer> getFrom() {
    return from;
  }

  public void setFrom(
      HashMap<Ingredient, Integer> from) {
    this.from = from;
  }

  public HashMap<Ingredient, Integer> getTo() {
    return to;
  }

  public void setTo(
      HashMap<Ingredient, Integer> to) {
    this.to = to;
  }
}
