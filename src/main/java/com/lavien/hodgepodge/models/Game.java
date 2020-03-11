package com.lavien.hodgepodge.models;

import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.ArrayList;

public class Game {
  private ArrayList<Alchemist> alchemists;
  private ArrayList<Merchant> availableMerchants;
  private ArrayList<Merchant> unavailableMerchants;
  private ArrayList<Mixture> availableMixtures;
  private ArrayList<Mixture> unavailableMixtures;

  public Game() {
  }

  public Game(ArrayList<Alchemist> alchemists, ArrayList<Merchant> availableMerchants, ArrayList<Merchant> unavailableMerchants, ArrayList<Mixture> availableMixtures, ArrayList<Mixture> unavailableMixtures) {
    this.alchemists = alchemists;
    this.availableMerchants = availableMerchants;
    this.unavailableMerchants = unavailableMerchants;
    this.availableMixtures = availableMixtures;
    this.unavailableMixtures = unavailableMixtures;
  }

  public ArrayList<Alchemist> getAlchemists() {
    return alchemists;
  }

  public void setAlchemists(ArrayList<Alchemist> alchemists) {
    this.alchemists = alchemists;
  }

  public ArrayList<Merchant> getAvailableMerchants() {
    return availableMerchants;
  }

  public void setAvailableMerchants(ArrayList<Merchant> availableMerchants) {
    this.availableMerchants = availableMerchants;
  }

  public ArrayList<Merchant> getUnavailableMerchants() {
    return unavailableMerchants;
  }

  public void setUnavailableMerchants(ArrayList<Merchant> unavailableMerchants) {
    this.unavailableMerchants = unavailableMerchants;
  }

  public ArrayList<Mixture> getAvailableMixtures() {
    return availableMixtures;
  }

  public void setAvailableMixtures(ArrayList<Mixture> availableMixtures) {
    this.availableMixtures = availableMixtures;
  }

  public ArrayList<Mixture> getUnavailableMixtures() {
    return unavailableMixtures;
  }

  public void setUnavailableMixtures(ArrayList<Mixture> unavailableMixtures) {
    this.unavailableMixtures = unavailableMixtures;
  }
}
