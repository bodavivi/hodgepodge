package com.lavien.hodgepodge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alchemists")
public class Alchemist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  private Game game;

  @ManyToMany(mappedBy = "alchemists")
  private List<Mixture> mixtures;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "played_merchant_alchemist",
      joinColumns = @JoinColumn(name = "alchemist_id"),
      inverseJoinColumns = @JoinColumn(name = "merchant_id"))
  private List<Merchant> playedMerchants;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(name = "hand_merchant_alchemist",
      joinColumns = @JoinColumn(name = "alchemist_id"),
      inverseJoinColumns = @JoinColumn(name = "merchant_id"))
  private List<Merchant> merchantsInHand;

  private int ingrRoot;
  private int ingrMushroom;
  private int ingrFeather;
  private int ingrChickenLeg;

  private int goldCoins;
  private int silverCoins;

  public Alchemist() {
    this.game = null;
    this.mixtures = new ArrayList<>();
    this.playedMerchants = new ArrayList<>();
    this.merchantsInHand = new ArrayList<>();
    this.ingrRoot = 0;
    this.ingrMushroom = 0;
    this.ingrFeather = 0;
    this.ingrChickenLeg = 0;
    this.goldCoins = 0;
    this.silverCoins = 0;
  }

}
