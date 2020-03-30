package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.merchants.Merchant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private int value;

  @ManyToMany(mappedBy = "ingredients")
  private List<Mixture> mixtures;

  @Transient
  @ManyToMany
  private List<Alchemist> alchemists;
  @Transient
  @ManyToMany
  private List<Merchant> merchants;

  public Ingredient(String name, int value) {
    this.name = name;
    this.value = value;
    this.mixtures = new ArrayList<>();
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
