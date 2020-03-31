package com.lavien.hodgepodge.models.merchants;

public class UpdateIngrMerchant extends Merchant {
  private int ingrRootFrom;
  private int ingrMushroomFrom;
  private int ingrFeatherFrom;
  private int ingrMushroomTo;
  private int ingrFeatherTo;
  private int ingrChickenLegTo;


  public UpdateIngrMerchant(int ingrRootFrom, int ingrMushroomFrom, int ingrFeatherFrom, int ingrMushroomTo, int ingrFeatherTo, int ingrChickenLegTo) {
    this.ingrRootFrom = ingrRootFrom;
    this.ingrMushroomFrom = ingrMushroomFrom;
    this.ingrFeatherFrom = ingrFeatherFrom;
    this.ingrMushroomTo = ingrMushroomTo;
    this.ingrFeatherTo = ingrFeatherTo;
    this.ingrChickenLegTo = ingrChickenLegTo;
  }

  public int getIngrRootFrom() {
    return ingrRootFrom;
  }

  public void setIngrRootFrom(int ingrRootFrom) {
    this.ingrRootFrom = ingrRootFrom;
  }

  public int getIngrMushroomFrom() {
    return ingrMushroomFrom;
  }

  public void setIngrMushroomFrom(int ingrMushroomFrom) {
    this.ingrMushroomFrom = ingrMushroomFrom;
  }

  public int getIngrFeatherFrom() {
    return ingrFeatherFrom;
  }

  public void setIngrFeatherFrom(int ingrFeatherFrom) {
    this.ingrFeatherFrom = ingrFeatherFrom;
  }

  public int getIngrMushroomTo() {
    return ingrMushroomTo;
  }

  public void setIngrMushroomTo(int ingrMushroomTo) {
    this.ingrMushroomTo = ingrMushroomTo;
  }

  public int getIngrFeatherTo() {
    return ingrFeatherTo;
  }

  public void setIngrFeatherTo(int ingrFeatherTo) {
    this.ingrFeatherTo = ingrFeatherTo;
  }

  public int getIngrChickenLegTo() {
    return ingrChickenLegTo;
  }

  public void setIngrChickenLegTo(int ingrChickenLegTo) {
    this.ingrChickenLegTo = ingrChickenLegTo;
  }
}
