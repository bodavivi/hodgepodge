package com.lavien.hodgepodge.models.merchants;

public class UpdateIngrMerchant extends Merchant {

  private int maxUpgrade;

  public UpdateIngrMerchant(int maxUpgrade) {
    this.maxUpgrade = maxUpgrade;
  }

  public int getMaxUpgrade() {
    return maxUpgrade;
  }

  public void setMaxUpgrade(int maxUpgrade) {
    this.maxUpgrade = maxUpgrade;
  }
}
