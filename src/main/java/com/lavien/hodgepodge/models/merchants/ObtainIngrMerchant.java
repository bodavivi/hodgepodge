package com.lavien.hodgepodge.models.merchants;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "obtain")
public class ObtainIngrMerchant extends Merchant {

  @Column(name = "obtain_root")
  private int obtainRoot;
  @Column(name = "obtain_mushroom")
  private int obtainMushroom;
  @Column(name = "obtain_feather")
  private int obtainFeather;
  @Column(name = "obtain_chicken_leg")
  private int obtainChickenLeg;

  public ObtainIngrMerchant() {
  }

  public ObtainIngrMerchant(int ingrRootToGet, int ingrMushroomToGet, int ingrFeatherToGet, int ingrChickenLegToGet) {
    this.obtainRoot = ingrRootToGet;
    this.obtainMushroom = ingrMushroomToGet;
    this.obtainFeather = ingrFeatherToGet;
    this.obtainChickenLeg = ingrChickenLegToGet;
  }

  public int getObtainRoot() {
    return obtainRoot;
  }

  public void setObtainRoot(int obtainRoot) {
    this.obtainRoot = obtainRoot;
  }

  public int getObtainMushroom() {
    return obtainMushroom;
  }

  public void setObtainMushroom(int obtainMushroom) {
    this.obtainMushroom = obtainMushroom;
  }

  public int getObtainFeather() {
    return obtainFeather;
  }

  public void setObtainFeather(int obtainFeather) {
    this.obtainFeather = obtainFeather;
  }

  public int getObtainChickenLeg() {
    return obtainChickenLeg;
  }

  public void setObtainChickenLeg(int obtainChickenLeg) {
    this.obtainChickenLeg = obtainChickenLeg;
  }
}
