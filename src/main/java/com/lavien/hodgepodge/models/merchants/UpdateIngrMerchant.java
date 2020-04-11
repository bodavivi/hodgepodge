package com.lavien.hodgepodge.models.merchants;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "update_m")
public class UpdateIngrMerchant extends Merchant {

  @Column(name = "update_from_root")
  private int updateFromRoot;
  @Column(name = "update_from_mushroom")
  private int updateFromMushroom;
  @Column(name = "update_from_feather")
  private int updateFromFeather;
  @Column(name = "update_to_mushroom")
  private int updateToMushroom;
  @Column(name = "update_to_feather")
  private int updateToFeather;
  @Column(name = "update_to_chicken_leg")
  private int updateToChickenLeg;

  public UpdateIngrMerchant() {
  }

  public UpdateIngrMerchant(int ingrRootFrom, int ingrMushroomFrom, int ingrFeatherFrom,
      int ingrMushroomTo, int ingrFeatherTo, int ingrChickenLegTo) {
    this.updateFromRoot = ingrRootFrom;
    this.updateFromMushroom = ingrMushroomFrom;
    this.updateFromFeather = ingrFeatherFrom;
    this.updateToMushroom = ingrMushroomTo;
    this.updateToFeather = ingrFeatherTo;
    this.updateToChickenLeg = ingrChickenLegTo;
  }

  public int getUpdateFromRoot() {
    return updateFromRoot;
  }

  public void setUpdateFromRoot(int updateFromRoot) {
    this.updateFromRoot = updateFromRoot;
  }

  public int getUpdateFromMushroom() {
    return updateFromMushroom;
  }

  public void setUpdateFromMushroom(int updateFromMushroom) {
    this.updateFromMushroom = updateFromMushroom;
  }

  public int getUpdateFromFeather() {
    return updateFromFeather;
  }

  public void setUpdateFromFeather(int updateFromFeather) {
    this.updateFromFeather = updateFromFeather;
  }

  public int getUpdateToMushroom() {
    return updateToMushroom;
  }

  public void setUpdateToMushroom(int updateToMushroom) {
    this.updateToMushroom = updateToMushroom;
  }

  public int getUpdateToFeather() {
    return updateToFeather;
  }

  public void setUpdateToFeather(int updateToFeather) {
    this.updateToFeather = updateToFeather;
  }

  public int getUpdateToChickenLeg() {
    return updateToChickenLeg;
  }

  public void setUpdateToChickenLeg(int updateToChickenLeg) {
    this.updateToChickenLeg = updateToChickenLeg;
  }
}
