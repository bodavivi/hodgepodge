package com.lavien.hodgepodge.models.merchants;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "trade_m")
public class TradeIngrMerchant extends Merchant {

  @Column(name = "trade_root_from")
  private int tradeFromRoot;
  @Column(name = "trade_root_to")
  private int tradeToRoot;
  @Column(name = "trade_mushroom_from")
  private int tradeFromMushroom;
  @Column(name = "trade_mushroom_to")
  private int tradeToMushroom;
  @Column(name = "trade_feather_from")
  private int tradeFromFeather;
  @Column(name = "trade_feather_to")
  private int tradeToFeather;
  @Column(name = "trade_chicken_leg_from")
  private int tradeFromChickenLeg;
  @Column(name = "trade_chicken_leg_to")
  private int tradeToChickenLeg;

  public TradeIngrMerchant() {
  }

  public TradeIngrMerchant(int ingrRootFrom, int ingrRootTo, int ingrMushroomFrom,
      int ingrMushroomTo, int ingrFeatherFrom, int ingrFeatherTo, int ingrChickenLegFrom,
      int ingrChickenLegTo) {
    this.tradeFromRoot = ingrRootFrom;
    this.tradeToRoot = ingrRootTo;
    this.tradeFromMushroom = ingrMushroomFrom;
    this.tradeToMushroom = ingrMushroomTo;
    this.tradeFromFeather = ingrFeatherFrom;
    this.tradeToFeather = ingrFeatherTo;
    this.tradeFromChickenLeg = ingrChickenLegFrom;
    this.tradeToChickenLeg = ingrChickenLegTo;
  }

  public int getTradeFromRoot() {
    return tradeFromRoot;
  }

  public void setTradeFromRoot(int tradeFromRoot) {
    this.tradeFromRoot = tradeFromRoot;
  }

  public int getTradeToRoot() {
    return tradeToRoot;
  }

  public void setTradeToRoot(int tradeToRoot) {
    this.tradeToRoot = tradeToRoot;
  }

  public int getTradeFromMushroom() {
    return tradeFromMushroom;
  }

  public void setTradeFromMushroom(int tradeFromMushroom) {
    this.tradeFromMushroom = tradeFromMushroom;
  }

  public int getTradeToMushroom() {
    return tradeToMushroom;
  }

  public void setTradeToMushroom(int tradeToMushroom) {
    this.tradeToMushroom = tradeToMushroom;
  }

  public int getTradeFromFeather() {
    return tradeFromFeather;
  }

  public void setTradeFromFeather(int tradeFromFeather) {
    this.tradeFromFeather = tradeFromFeather;
  }

  public int getTradeToFeather() {
    return tradeToFeather;
  }

  public void setTradeToFeather(int tradeToFeather) {
    this.tradeToFeather = tradeToFeather;
  }

  public int getTradeFromChickenLeg() {
    return tradeFromChickenLeg;
  }

  public void setTradeFromChickenLeg(int tradeFromChickenLeg) {
    this.tradeFromChickenLeg = tradeFromChickenLeg;
  }

  public int getTradeToChickenLeg() {
    return tradeToChickenLeg;
  }

  public void setTradeToChickenLeg(int tradeToChickenLeg) {
    this.tradeToChickenLeg = tradeToChickenLeg;
  }
}
