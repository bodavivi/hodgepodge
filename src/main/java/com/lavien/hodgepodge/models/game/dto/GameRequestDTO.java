package com.lavien.hodgepodge.models.game.dto;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Mixture;
import com.lavien.hodgepodge.models.merchants.Merchant;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameRequestDTO {

  @NotBlank
  private String gameCode;

  private List<Merchant> unavailableMerchants;

  private List<Merchant> availableMerchants;

  private List<Alchemist> alchemists;

  private List<Mixture> availableMixtures;

  private List<Mixture> unavailableMixtures;

}
