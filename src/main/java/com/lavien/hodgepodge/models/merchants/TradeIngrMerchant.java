package com.lavien.hodgepodge.models.merchants;

public class TradeIngrMerchant extends Merchant {

  private int ingrRootFrom;
  private int ingrRootTo;
  private int ingrMushroomFrom;
  private int ingrMushroomTo;
  private int ingrFeatherFrom;
  private int ingrFeatherTo;
  private int ingrChickenLegFrom;
  private int ingrChickenLegTo;

  public TradeIngrMerchant(int ingrRootFrom, int ingrRootTo, int ingrMushroomFrom, int ingrMushroomTo,
      int ingrFeatherFrom, int ingrFeatherTo, int ingrChickenLegFrom, int ingrChickenLegTo) {
    this.ingrRootFrom = ingrRootFrom;
    this.ingrRootTo = ingrRootTo;
    this.ingrMushroomFrom = ingrMushroomFrom;
    this.ingrMushroomTo = ingrMushroomTo;
    this.ingrFeatherFrom = ingrFeatherFrom;
    this.ingrFeatherTo = ingrFeatherTo;
    this.ingrChickenLegFrom = ingrChickenLegFrom;
    this.ingrChickenLegTo = ingrChickenLegTo;
  }

  public int getIngrRootFrom() {
    return ingrRootFrom;
  }

  public void setIngrRootFrom(int ingrRootFrom) {
    this.ingrRootFrom = ingrRootFrom;
  }

  public int getIngrRootTo() {
    return ingrRootTo;
  }

  public void setIngrRootTo(int ingrRootTo) {
    this.ingrRootTo = ingrRootTo;
  }

  public int getIngrMushroomFrom() {
    return ingrMushroomFrom;
  }

  public void setIngrMushroomFrom(int ingrMushroomFrom) {
    this.ingrMushroomFrom = ingrMushroomFrom;
  }

  public int getIngrMushroomTo() {
    return ingrMushroomTo;
  }

  public void setIngrMushroomTo(int ingrMushroomTo) {
    this.ingrMushroomTo = ingrMushroomTo;
  }

  public int getIngrFeatherFrom() {
    return ingrFeatherFrom;
  }

  public void setIngrFeatherFrom(int ingrFeatherFrom) {
    this.ingrFeatherFrom = ingrFeatherFrom;
  }

  public int getIngrFeatherTo() {
    return ingrFeatherTo;
  }

  public void setIngrFeatherTo(int ingrFeatherTo) {
    this.ingrFeatherTo = ingrFeatherTo;
  }

  public int getIngrChickenLegFrom() {
    return ingrChickenLegFrom;
  }

  public void setIngrChickenLegFrom(int ingrChickenLegFrom) {
    this.ingrChickenLegFrom = ingrChickenLegFrom;
  }

  public int getIngrChickenLegTo() {
    return ingrChickenLegTo;
  }

  public void setIngrChickenLegTo(int ingrChickenLegTo) {
    this.ingrChickenLegTo = ingrChickenLegTo;
  }
}
