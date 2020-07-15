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
@Entity(name = "trade")
public class TradeIngrMerchant extends Merchant {

  @Column(name = "trade_root_from")
  private int tradeFromRoot;
  @Column(name = "trade_root_to")
  private int tradeToRoot;
  @Column(name = "trade_mushroom_from")
  private int tradeFromMushroom;
  @Column(name = "trade_mushroom_to")
  private int tradeToMushroom;
  @Column(name = "trade_feather_from")
  private int tradeFromFeather;
  @Column(name = "trade_feather_to")
  private int tradeToFeather;
  @Column(name = "trade_chicken_leg_from")
  private int tradeFromChickenLeg;
  @Column(name = "trade_chicken_leg_to")
  private int tradeToChickenLeg;

}
