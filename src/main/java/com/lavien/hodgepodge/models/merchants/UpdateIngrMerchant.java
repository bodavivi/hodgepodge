package com.lavien.hodgepodge.models.merchants;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "update_m")
public class UpdateIngrMerchant extends Merchant {

  @Column(name = "number_of_updates")
  private int numberOfUpdates;

  public UpdateIngrMerchant() {
  }

  public UpdateIngrMerchant(int numberOfUpdates) {
    this.numberOfUpdates = numberOfUpdates;
  }

  public int getNumberOfUpdates() {
    return numberOfUpdates;
  }

  public void setNumberOfUpdates(int numberOfUpdates) {
    this.numberOfUpdates = numberOfUpdates;
  }
}
