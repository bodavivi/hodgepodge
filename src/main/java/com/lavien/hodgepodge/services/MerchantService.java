package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.merchants.Merchant;

import java.util.List;

public interface MerchantService {
  List<Merchant> findAll();
  List<Merchant> findStarterUnavailableMerchants();
  Merchant save(Merchant merchant);
}
