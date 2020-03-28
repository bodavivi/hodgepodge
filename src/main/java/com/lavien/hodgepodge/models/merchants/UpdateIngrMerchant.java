package com.lavien.hodgepodge.models.merchants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UpdateIngrMerchant extends Merchant {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
