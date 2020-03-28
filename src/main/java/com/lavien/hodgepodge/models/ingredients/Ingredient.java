package com.lavien.hodgepodge.models.ingredients;

import com.lavien.hodgepodge.models.Alchemist;
import com.lavien.hodgepodge.models.Mixture;
import com.lavien.hodgepodge.models.merchants.Merchant;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private int value;
  @ManyToMany
  private List<Alchemist> alchemists;
  @ManyToMany
  private List<Merchant> merchants;
  @ManyToMany
  private List<Mixture> mixtures;

  public Ingredient(String name, int value) {
    this.name = name;
    this.value = value;
  }

  Ingredient(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
