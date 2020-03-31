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
}
