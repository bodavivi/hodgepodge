package com.lavien.hodgepodge.models.merchants;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
