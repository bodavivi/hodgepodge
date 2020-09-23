package com.lavien.hodgepodge.models.game.dto;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Mixture;
import com.lavien.hodgepodge.models.merchants.Merchant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDTO {

  private Long id;

  private String gameCode;

  private List<Merchant> unavailableMerchants;

  private List<Merchant> availableMerchants;

  private List<Alchemist> alchemists;

  private List<Mixture> availableMixtures;

  private List<Mixture> unavailableMixtures;

}
