package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
  private MerchantRepository merchantRepository;

  @Autowired
  public MerchantServiceImpl(MerchantRepository merchantRepository) {
    this.merchantRepository = merchantRepository;
  }

  @Override
  public List<Merchant> findAll() {
    List<Merchant> allMerchant = new ArrayList<>();
    for (Merchant merchant : merchantRepository.findAll()) {
      allMerchant.add(merchant);
    }
    return allMerchant;
  }

  @Override
  public List<Merchant> findStarterUnavailableMerchants() {
    List<Merchant> merchants = new ArrayList<>();
    for (Merchant merchant : merchantRepository.findAll()) {
      if (merchant.getId() != 100 || merchant.getId() != 200) {
        merchants.add(merchant);
      }
    }
    return merchants;
  }

  @Override
  public Merchant save(Merchant merchant){
    return merchantRepository.save(merchant);
  }

}
