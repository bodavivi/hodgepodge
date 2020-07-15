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
@Entity(name = "update_m")
public class UpdateIngrMerchant extends Merchant {

  @Column(name = "number_of_updates")
  private int numberOfUpdates;

}
