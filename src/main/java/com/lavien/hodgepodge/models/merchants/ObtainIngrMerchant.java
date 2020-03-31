package com.lavien.hodgepodge.models.merchants;

public class ObtainIngrMerchant extends Merchant {
  private int ingrRootToGet;
  private int ingrMushroomToGet;
  private int ingrFeatherToGet;
  private int ingrChickenLegToGet;

  public ObtainIngrMerchant(int ingrRootToGet, int ingrMushroomToGet, int ingrFeatherToGet, int ingrChickenLegToGet) {
    this.ingrRootToGet = ingrRootToGet;
    this.ingrMushroomToGet = ingrMushroomToGet;
    this.ingrFeatherToGet = ingrFeatherToGet;
    this.ingrChickenLegToGet = ingrChickenLegToGet;
  }

  public int getIngrRootToGet() {
    return ingrRootToGet;
  }

  public void setIngrRootToGet(int ingrRootToGet) {
    this.ingrRootToGet = ingrRootToGet;
  }

  public int getIngrMushroomToGet() {
    return ingrMushroomToGet;
  }

  public void setIngrMushroomToGet(int ingrMushroomToGet) {
    this.ingrMushroomToGet = ingrMushroomToGet;
  }

  public int getIngrFeatherToGet() {
    return ingrFeatherToGet;
  }

  public void setIngrFeatherToGet(int ingrFeatherToGet) {
    this.ingrFeatherToGet = ingrFeatherToGet;
  }

  public int getIngrChickenLegToGet() {
    return ingrChickenLegToGet;
  }

  public void setIngrChickenLegToGet(int ingrChickenLegToGet) {
    this.ingrChickenLegToGet = ingrChickenLegToGet;
  }
}
