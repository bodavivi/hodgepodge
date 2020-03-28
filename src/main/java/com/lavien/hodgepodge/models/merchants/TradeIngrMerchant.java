package com.lavien.hodgepodge.models.merchants;

import com.lavien.hodgepodge.models.ingredients.Ingredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class TradeIngrMerchant extends Merchant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private HashMap<Ingredient, Integer> from;
  private HashMap<Ingredient, Integer> to;

  public TradeIngrMerchant(HashMap<Ingredient, Integer> from, HashMap<Ingredient, Integer> to) {
    this.from = from;
    this.to = to;
  }

  public HashMap<Ingredient, Integer> getFrom() {
    return from;
  }

  public void setFrom(HashMap<Ingredient, Integer> from) {
    this.from = from;
  }

  public HashMap<Ingredient, Integer> getTo() {
    return to;
  }

  public void setTo(HashMap<Ingredient, Integer> to) {
    this.to = to;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
